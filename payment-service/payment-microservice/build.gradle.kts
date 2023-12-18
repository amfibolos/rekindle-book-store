plugins {
    id("rekindle.book.store.java-application-conventions")
    alias { libs.plugins.spring.boot.plugin }
    alias { libs.plugins.spring.dependency.management }
    alias { libs.plugins.google.jib }
}
dependencies {
    implementation(project(":payment-service:payment-application-service"))
    implementation(project(":payment-service:payment-rest-adapter"))
    implementation(project(":payment-service:payment-orm-adapter"))
    implementation(project(":payment-service:payment-messaging-adapter"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.data:spring-data-jpa")
    implementation("org.springframework.cloud:spring-cloud-starter-config")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
}
extra["springCloudVersion"] = "2023.0.0"
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}
application {
    mainClass.set("com.rekindle.book.store.payment.microservice.PaymentServiceApplication")
}
jib {
    from.image = "amazoncorretto:21.0.1"
}