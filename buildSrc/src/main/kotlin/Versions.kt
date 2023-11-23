import org.gradle.api.JavaVersion
import org.gradle.api.Project

object Versions {

    // region Common

    const val kotlin = "1.9.0"
    const val coroutines = "1.6.4"
    const val kotlinxDateTime = "0.3.0"
    const val kotlinSerialization = "1.3.1"
    const val navigation = "2.5.3"
    const val realm = "1.5.2"

    // endregion

    object Android {
        const val compileSdkVersion = 33
        const val minSdkVersion = 23
        const val targetSdkVersion = 33
    }

    object Plugins {
        const val agpVersion = "8.1.4"
        const val kgpVersion = "1.9.0"

        const val desugaring = "1.2.2"


        const val googlePlayServices = "4.3.10"

        const val undercouch = "4.1.2"
    }

    object Java {
        val sourceCompatibility = JavaVersion.VERSION_17
        val targetCompatibility = JavaVersion.VERSION_17
    }

    object Kotlin {
        /**
         * Keep "1.8" instead of "11" to fix this issue: Cannot inline bytecode built with
         * JVM target 11 into bytecode that is being built with JVM target 1.8.
         */
        const val jvmTarget = "1.8"
    }
}
