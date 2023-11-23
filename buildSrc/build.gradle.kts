//
//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//        gradlePluginPortal()
//    }
//    dependencies {
//        classpath(Dependencies.Plugins.ANDROID_GRADLE_PLUGIN)
//        //classpath(Dependencies.Plugins.KOTLIN_GRADLE_PLUGIN)
//    }
//}

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`

}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

dependencies {
    compileOnly(Dependencies.Plugins.ANDROID_GRADLE_PLUGIN)
    compileOnly(Dependencies.Plugins.KOTLIN_GRADLE_PLUGIN)
}

tasks.withType<Test>().configureEach {
    testLogging { showStandardStreams = true }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}
tasks.getByName<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}
kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
    }
}