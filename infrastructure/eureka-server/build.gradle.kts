plugins {
    id("rekindle.book.store.java-application-conventions")
    alias { libs.plugins.spring.boot.plugin }
    alias { libs.plugins.spring.dependency.management }
    alias { libs.plugins.google.jib }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-server")
}
extra["springCloudVersion"] = "2023.0.0"
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}
application {
    mainClass.set("com.rekindle.book.store.server.eureka.EurekaServerApplication")
}
jib {
    from.image = "amazoncorretto:21.0.1"
}