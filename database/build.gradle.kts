import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot")

    kotlin("jvm")
    kotlin("plugin.spring")
}

val mysqlVersion: String by extra

tasks.getByName<BootJar>("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

dependencies {
    val springDoc  = "1.5.2"

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))


    implementation(project(":enums"))

    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
    implementation(kotlin("stdlib-jdk8"))



    api("org.springdoc:springdoc-openapi-ui:${springDoc}")
    api("org.springdoc:springdoc-openapi-data-rest:${springDoc}")
    api("org.springdoc:springdoc-openapi-kotlin:${springDoc}")

    api("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.h2database:h2")
    implementation("mysql:mysql-connector-java:8.0.20")
}
