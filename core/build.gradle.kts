import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    val coroutinesVersion = "1.4.3"

    implementation(project(":config"))
    implementation(project(":enums"))
    implementation(project(":database"))

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.springframework.boot:spring-boot-starter-web")

    api("org.springframework.boot:spring-boot-starter-data-jpa")
    api("org.springframework.boot:spring-boot-starter-security")
//    api("org.springframework.security:spring-security-crypto")
    api("org.springframework.boot:spring-boot-starter-validation")

    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:${coroutinesVersion}")

    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.3")
    api("commons-codec:commons-codec:1.15")
    api("com.github.rozidan:modelmapper-spring-boot-starter:2.3.1")
    api("joda-time:joda-time:2.10.10")

    implementation("com.github.rholder.fauxflake:fauxflake-core:1.1.0")
    implementation("com.googlecode.libphonenumber:libphonenumber:8.12.22")
    implementation("org.apache.commons:commons-collections4:4.4")

    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}
