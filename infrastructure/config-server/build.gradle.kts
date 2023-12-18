plugins {
    id("rekindle.book.store.java-application-conventions")
    alias { libs.plugins.spring.boot.plugin }
    alias { libs.plugins.spring.dependency.management }
    alias { libs.plugins.google.jib }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}
extra["springCloudVersion"] = "2023.0.0"
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}
application {
    mainClass.set("com.rekindle.book.store.server.configuration.ConfigServerApplication")
}
jib {
    from.image = "amazoncorretto:21.0.1"
}
