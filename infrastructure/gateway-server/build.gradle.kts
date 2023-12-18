plugins {
    id("rekindle.book.store.java-application-conventions")
    alias { libs.plugins.spring.boot.plugin }
    alias { libs.plugins.spring.dependency.management }
    alias { libs.plugins.google.jib }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-gateway")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}
extra["springCloudVersion"] = "2023.0.0"
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}
application {
    mainClass.set("com.rekindle.book.store.server.eureka.GatewayServerApplication")
}
jib {
    from.image = "amazoncorretto:21.0.1"
}