import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.jvm.toolchain.JavaLanguageVersion

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
        const val compileSdkVersion = 34
        const val minSdkVersion = 23
        const val targetSdkVersion = 34
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
        val javaToolchainVersion = JavaLanguageVersion.of(17)
    }

    object Kotlin {
        const val jvmTarget = "17"
    }
}
