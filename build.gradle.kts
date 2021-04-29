import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

buildscript {
    repositories {
        mavenCentral()
        mavenLocal()
        jcenter()
    }
}

plugins {
    val kotlinVersion = "1.4.30"
    val springVersion = "2.4.2"


    id("org.springframework.boot") version springVersion apply false
    id("io.spring.dependency-management") version "1.0.10.RELEASE" apply false
    id("name.remal.check-updates") version "1.0.211" apply false

    kotlin("jvm") version kotlinVersion apply false
    kotlin("plugin.spring") version kotlinVersion apply false
    kotlin("plugin.jpa") version kotlinVersion apply false
}


allprojects {
    val date = Calendar.getInstance()
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.BASIC_ISO_DATE
    val timestamp = current.format(formatter)

    val versionNumber: String? = "4"//date.get(Calendar.DAY_OF_YEAR).toString()
    val minorRelease: String? = date.get(Calendar.MONTH).toString()
    var buildNumber: String? = System.getenv("CIRCLE_BUILD_NUM")
    val revisionNumber: String? = timestamp
    when {
        buildNumber.isNullOrBlank() -> buildNumber = date.get(Calendar.WEEK_OF_YEAR).toString()
    }
    group = "com.iita"
    version = "$versionNumber.$minorRelease.$buildNumber.$revisionNumber"

    tasks.withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

repositories {
    mavenCentral()
}

subprojects {
    repositories {
        mavenCentral()
        mavenLocal()
        maven {
            url = uri("https://jitpack.io")
        }
    }
    apply {
        plugin("io.spring.dependency-management")
        plugin("name.remal.check-updates")
    }
}
