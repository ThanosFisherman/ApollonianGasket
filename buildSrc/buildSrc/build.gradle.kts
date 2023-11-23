plugins {
    `kotlin-dsl`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

// force compilation of Dependencies.kt and Versions.kt so they can be referenced in buildSrc/build.gradle.kts
sourceSets.main {
    java {
        setSrcDirs(setOf(projectDir.parentFile.resolve("src/main/kotlin")))
        include("Dependencies.kt")
        include("Versions.kt")
    }
}
