pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://teavm.org/maven/repository/")
        google()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "libgdx-bootstrapper"
include("teavm")
include("desktop")
include("core")
include("android")
