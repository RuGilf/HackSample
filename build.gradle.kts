plugins {
    kotlin("jvm") version "2.4.0"
    kotlin("plugin.serialization") version "2.4.0"
    id("io.ktor.plugin") version "3.5.2"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Плагины
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-netty")

    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-kotlinx-json")

    implementation("io.ktor:ktor-server-status-pages")

    implementation("at.favre.lib:bcrypt:0.10.2")

    implementation("org.jetbrains.exposed:exposed-core:1.4.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:1.4.0")
    implementation("org.postgresql:postgresql:42.7.13")


    // Плагины для тестирования
    testImplementation(kotlin("test"))

    testImplementation("org.testcontainers:testcontainers-postgresql:2.0.5")
    testImplementation("org.testcontainers:testcontainers-junit-jupiter:2.0.5")
}

kotlin {
    jvmToolchain(25)
}

application {
    mainClass.set("MainKt")
}

tasks.test {
    useJUnitPlatform()
}