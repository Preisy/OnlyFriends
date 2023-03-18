import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    id("org.springframework.boot") version "3.0.4"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.serialization") version "1.4.21"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
}

group = "ru.only-friends"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    runtimeOnly("org.mariadb.jdbc:mariadb-java-client")


//    implementation("org.springframework.boot:spring-boot-configuration-processor")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//    implementation("io.jsonwebtoken:jjwt:0.9.1")
//    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
//    compileOnly("commons-io:commons-io:2.11.0")
//    compileOnly("javax.xml.bind:jaxb-api:2.3.1")
//    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
//
//    implementation("jakarta.xml.bind:jakarta.xml.bind-api:2.3.3")
//    implementation("javax.xml.bind:jaxb-api:2.3.0")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4")
    implementation("io.jsonwebtoken:jjwt:0.9.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    compileOnly("commons-io:commons-io:2.11.0")
    compileOnly("javax.xml.bind:jaxb-api:2.3.1")
    compileOnly("javax.servlet:javax.servlet-api:3.0.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
