import java.util.*

val configServer = project(":infrastructure:config-server")
val eurekaServer = project(":infrastructure:eureka-server")
val authorizationServer = project(":infrastructure:authorization-server")
val gatewayServer = project(":infrastructure:gateway-server")

fun getJarPath(module: Project): String {
    val projectDir = project(":" + module.path).projectDir
    return "$projectDir/build/libs/${module.name}.jar"
}

//fun startUpProject(project: Project) {
//    tasks.create("start-${project.name}") {
//        exec {
//            commandLine("nohup", "java", "-jar", getJarPath(project), "&")
//        }
//    }
//}

fun healthCheck(url: String, intervalMillis: Long, timeoutMillis: Long, retries: Int) {
    var attempts = 0
    var success = false
    while (attempts < retries && !success) {
        try {
            val process = ProcessBuilder("sh", "-c", "curl --fail --silent $url | grep UP")
                .start()
            process.waitFor(timeoutMillis, TimeUnit.MILLISECONDS)
            success = process.exitValue() == 0
            attempts++
        } catch (e: Exception) {
            attempts++
        }
        if (!success) {
            Thread.sleep(intervalMillis)
        }
    }
    if (!success) {
        throw RuntimeException("Health check failed after $retries attempts")
    }
}

fun startUpProject(project: Project, healthCheckUrl: String) {
    val jarPath = layout.projectDirectory.file("build/libs/${project.name}.jar")
    tasks.register<Exec>(
        "start${
            project.name.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }
        }"
    ) {
        doLast {
            println("Starting ${project.name}")
            exec {
                commandLine("nohup", "java", "-jar", jarPath, "&")
                isIgnoreExitValue = true
            }
            println("${project.name} has started. Waiting for health check success...")
            healthCheck(healthCheckUrl, 10 * 1000, 5 * 1000, 10)
        }
    }
}
val startConfigServer = tasks.register("startConfigServer") {
    doLast {
        startUpProject(configServer, "localhost:10599/actuator/health/readiness")
    }
}

val startEurekaServer = tasks.register("startEurekaServer") {
    doLast {
        startUpProject(eurekaServer, "localhost:10598/actuator/health/readiness")
    }
}

val startAuthorizationServer = tasks.register("startAuthorizationServer") {
    doLast {
        startUpProject(authorizationServer, "localhost:10597/actuator/health/readiness")
    }
}

val startGatewayServer = tasks.register("startGatewayServer") {
    doLast {
        startUpProject(gatewayServer, "localhost:10596/actuator/health/readiness")
    }
}

// Define dependencies between tasks using the finalizedBy method
startEurekaServer.configure {
    finalizedBy(startConfigServer)
}

startAuthorizationServer.configure {
    finalizedBy(startEurekaServer)
}

startGatewayServer.configure {
    finalizedBy(startAuthorizationServer)
}

// The start-app-local task will now only depend on startGatewayServer
// Since the other tasks have been linked with finalizedBy, they will execute in the right order
tasks.register("start-app-local") {
    dependsOn(startGatewayServer)
}