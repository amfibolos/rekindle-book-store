import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
plugins {
    id("rekindle.book.store.java-library-conventions")
    alias { libs.plugins.spring.boot.plugin }.apply(false)
    alias { libs.plugins.spring.dependency.management }
    id("org.flywaydb.flyway") version "10.3.0"
}
the<DependencyManagementExtension>().apply {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}
dependencies{
    implementation("org.flywaydb:flyway-database-postgresql:10.3.0")
    implementation("org.flywaydb:flyway-core:10.3.0")
    implementation("org.postgresql:postgresql")
}
buildscript{
    dependencies{
        classpath("org.flywaydb:flyway-database-postgresql:10.3.0")
    }
}
flyway{
    url = "jdbc:postgresql://localhost:5432/postgres"
    user = "postgres"
    password = "admin"
    driver = "org.postgresql.Driver"
}