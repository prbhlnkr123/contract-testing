import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.spring") version "1.9.23"
}

group = "org.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("au.com.dius.pact.consumer:junit5:4.6.5")
    testImplementation("au.com.dius.pact.provider:junit5spring:4.6.9")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.register<Test>("runConsumerTest"){
    description = "Runs consumer tests"
    group = "verification"

    // Specify the test classes/packages to include
    include("org/example/consumer/*ConsumerTest.class")
}

tasks.register<Test>("runProviderTest"){
    description = "Runs provider tests"
    group = "verification"

    // Specify the test classes/packages to include
    include("org/example/provider/*ProviderPactTest.class")
}