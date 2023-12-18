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
    implementation(project(":domain:domain-application"))
    implementation(project(":bookstore-service:bookstore-application-service"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    api(libs.org.springdoc.springdoc.openapi.starter.webmvc.ui)
    api(libs.org.springdoc.springdoc.openapi.starter.common)
}