import kotlin.reflect.full.memberProperties

object Dependencies {

    object Plugins {
        // NOTE TO DEVS: Please keep alphabetical order for readability and extendability
        const val ANDROID_GRADLE_PLUGIN =
            "com.android.tools.build:gradle:${Versions.Plugins.agpVersion}"
        const val DESUGARING_PLUGIN =
            "com.android.tools:desugar_jdk_libs:${Versions.Plugins.desugaring}"
        const val GOOGLE_PLAY_SERVICES_GRADLE_PLUGIN =
            "com.google.gms:google-services:${Versions.Plugins.googlePlayServices}"

        const val KOTLIN_ALL_OPEN = "org.jetbrains.kotlin:kotlin-allopen:${Versions.kotlin}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugins.kgpVersion}"
        const val KOTLIN_SERIALIZATION =
            "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"

        const val SAFE_ARGS_GRADLE_PLUGIN =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

        const val UNDERCOUCH = "de.undercouch:gradle-download-task:${Versions.Plugins.undercouch}"

        const val ANDROID_APPLICATION_APPLY = "com.android.application"
        const val ANDROID_LIBRARY_APPLY = "com.android.library"
        const val GOOGLE_PLAY_SERVICES_APPLY = "com.google.gms.google-services"
        const val JACOCO_APPLY = "jacoco"
        const val JAVA_LIBRARY_APPLY = "java-library"
        const val KOTLIN_ALL_OPEN_APPLY = "kotlin-allopen"
        const val KOTLIN_ANDROID_APPLY = "org.jetbrains.kotlin.android"
        const val KOTLIN_ANDROID_EXTENSIONS_APPLY = "kotlin-android-extensions"
        const val KOTLIN_JVM_APPLY = "org.jetbrains.kotlin.jvm"
        const val KOTLIN_KAPT_APPLY = "kotlin-kapt"
        const val KOTLIN_PARCELIZE_APPLY = "kotlin-parcelize"
        const val KOTLIN_SERIALIZATION_APPLY = "kotlinx-serialization"
        const val REALM_APPLY = "io.realm.kotlin"
        const val SAFE_ARGS_APPLY = "androidx.navigation.safeargs.kotlin"
        const val SONAR_PLUGIN_APPLY = "sonar-plugin"
        const val SONARQUBE_APPLY = "org.sonarqube"
        const val UNDERCOUCH_APPLY = "de.undercouch.download"
        // NOTE TO DEVS: Please keep alphabetical order for readability and extendability
    }

    object DesktopDependency {

        const val lwjgl3Backend = "com.badlogicgames.gdx:gdx-backend-lwjgl3:${Versions.Libgdx.gdxVersion}"
        const val nativesDesktop = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-desktop"
        const val controllers =
            "com.badlogicgames.gdx-controllers:gdx-controllers-desktop:${Versions.Libgdx.gdxControllersVersion}"

        fun getAll() =
            DesktopDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object AndroidDependency {

        const val androidBackend = "com.badlogicgames.gdx:gdx-backend-android:${Versions.Libgdx.gdxVersion}"
        const val controllers =
            "com.badlogicgames.gdx-controllers:gdx-controllers-android:${Versions.Libgdx.gdxControllersVersion}"

        const val arm64v8a = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-arm64-v8a"
        const val armeabiv7a = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-armeabi-v7a"
        const val nativesX86 = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-x86"
        const val nativesX86_64 = "com.badlogicgames.gdx:gdx-platform:${Versions.Libgdx.gdxVersion}:natives-x86_64"
        fun getAll() =
            AndroidDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object CoreDependency {

        const val coreLibgdx = "com.badlogicgames.gdx:gdx:${Versions.Libgdx.gdxVersion}"
        const val controllersCore =
            "com.badlogicgames.gdx-controllers:gdx-controllers-core:${Versions.Libgdx.gdxControllersVersion}"

        fun getAll() =
            CoreDependency::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object GraalDesktop {
        const val lwjgl3Backend = "com.github.Berstanio.gdx-graalhelper:gdx-svmhelper-backend-lwjgl3:-SNAPSHOT"
        const val controllersDesktop =
            "com.github.Berstanio.gdx-graalhelper:gdx-svmhelper-extension-controllers-lwjgl3:-SNAPSHOT"

        fun getAll() =
            GraalDesktop::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }

    object GraalCore {
        const val graalCore = "io.github.berstanio:gdx-svmhelper:1.11.1-SNAPSHOT"

        fun getAll() =
            GraalCore::class.memberProperties.filter { it.isConst }.map { it.getter.call().toString() }.toSet()
    }
}
