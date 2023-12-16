plugins {
    java
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://packages.confluent.io/maven/")
    }
}

dependencies {
//    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
