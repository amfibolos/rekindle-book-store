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
    implementation(project(":domain:domain-bookstore"))
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation(libs.springframework.spring.transactions)
    implementation("org.springframework.boot:spring-boot-starter-logging")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    constraints { annotationProcessor("org.projectlombok:lombok") }
}