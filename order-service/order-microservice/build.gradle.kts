plugins {
    id("rekindle.book.store.java-application-conventions")
    alias { libs.plugins.spring.boot.plugin }
    alias { libs.plugins.spring.dependency.management }
}
dependencies {
    implementation(project(":order-service:order-rest-adapter"))
    implementation(project(":order-service:order-orm-adapter"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
}

application {
    mainClass.set("com.rekindle.book.store.order.microservice.OrderServiceApplication")
}