import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    id("rekindle.book.store.java-library-conventions")
    alias { libs.plugins.spring.boot.plugin }.apply(false)
    alias { libs.plugins.spring.dependency.management }
}
the<DependencyManagementExtension>().apply {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}
dependencies {
    implementation(project(":bookstore-service:bookstore-application-service"))
    implementation(project(":domain:domain-bookstore"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.postgresql:postgresql")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}