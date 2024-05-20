
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
    implementation(Dependencies.Plugins.ANDROID_GRADLE_PLUGIN)
    implementation(Dependencies.Plugins.KOTLIN_GRADLE_PLUGIN)
}

tasks.withType<Test>().configureEach {
    testLogging { showStandardStreams = true }
}

gradlePlugin {
    plugins {
        register("base-plugin-kotlin") {
            id = "base-plugin-kotlin"
            implementationClass = "plugins.BasePluginKotlin"
        }

        register("base-plugin-android") {
            id = "base-plugin-android"
            implementationClass = "plugins.BasePluginAndroid"
        }
    }
}