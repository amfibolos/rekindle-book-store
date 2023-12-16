plugins {
    id("rekindle.book.store.java-application-conventions")
    alias { libs.plugins.spring.boot.plugin }
    alias { libs.plugins.spring.dependency.management }
}
dependencies {
    implementation(project(":bookstore-service:bookstore-rest-adapter"))
    implementation(project(":bookstore-service:bookstore-orm-adapter"))
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.data:spring-data-jpa")
}

application {
    mainClass.set("com.rekindle.book.store.order.microservice.OrderServiceApplication")
}