plugins {
    //id("com.android.application") version Versions.Plugins.agpVersion apply false
    //id("com.android.library") version Versions.Plugins.agpVersion apply false
    //id("org.jetbrains.kotlin.android") version Versions.Plugins.kgpVersion apply false
    //id("org.jetbrains.kotlin.jvm") version "1.9.21" apply false

}

buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Plugins.ANDROID_GRADLE_PLUGIN)
        classpath(Dependencies.Plugins.KOTLIN_GRADLE_PLUGIN)
    }
}

