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
    api(libs.org.apache.avro)
    implementation(libs.io.confluent.kafka.avro.serializer)
}