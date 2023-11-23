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
        const val REALM_GRADLE_PLUGIN = "io.realm.kotlin:gradle-plugin:${Versions.realm}"
        const val SAFE_ARGS_GRADLE_PLUGIN =
            "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

        const val UNDERCOUCH = "de.undercouch:gradle-download-task:${Versions.Plugins.undercouch}"

        const val ANDROID_APPLICATION_APPLY = "com.android.application"
        const val ANDROID_LIBRARY_APPLY = "com.android.library"
        const val GOOGLE_PLAY_SERVICES_APPLY = "com.google.gms.google-services"
        const val JACOCO_APPLY = "jacoco"
        const val JACOCO_REPORT_APPLY = "jacoco-report"
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
}
