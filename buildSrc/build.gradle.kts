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
    compileOnly(Deps.Plugins.ANDROID_GRADLE_PLUGIN)
    compileOnly(Deps.Plugins.KOTLIN_GRADLE_PLUGIN)
}

tasks.withType<Test>().configureEach {
    testLogging { showStandardStreams = true }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

kotlin {
    jvmToolchain(17)
}

gradlePlugin {
    plugins {
    }
}