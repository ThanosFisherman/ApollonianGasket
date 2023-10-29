import kotlin.reflect.full.memberProperties

object Deps {

    object Plugins {
        const val ANDROID_GRADLE_PLUGIN =
            "com.android.tools.build:gradle:${Versions.Plugins.agpVersion}"
        const val KOTLIN_GRADLE_PLUGIN =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Plugins.kgpVersion}"

        const val ANDROID_APPLICATION_APPLY = "com.android.application"
        const val ANDROID_LIBRARY_APPLY = "com.android.library"
        const val JAVA_LIBRARY_APPLY = "java-library"
        const val KOTLIN_ANDROID_APPLY = "org.jetbrains.kotlin.android"
        const val KOTLIN_JVM_APPLY = "org.jetbrains.kotlin.jvm"
    }
}
