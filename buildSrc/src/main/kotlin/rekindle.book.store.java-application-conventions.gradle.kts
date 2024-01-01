plugins {
    id("rekindle.book.store.java-common-conventions")
    application
}

tasks.withType<Jar> {
    destinationDirectory = File("${projectDir.path}/build/lib")
}