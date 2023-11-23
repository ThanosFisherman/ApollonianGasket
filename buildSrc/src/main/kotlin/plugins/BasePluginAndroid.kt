
package plugins

import BuildType
import Dependencies
import Versions
import com.android.build.gradle.BaseExtension
import coreLibraryDesugaring
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import kotlin.collections.set

/**
 * Configures the module as an android library.
 * Applies basic android and kotlin plugins and sets up build parameters.
 * Configures sonarqube for this module.
 */
class BasePluginAndroid : Plugin<Project> {
    override fun apply(project: Project) {
        val pluginManager = project.pluginManager

        pluginManager.apply(Dependencies.Plugins.ANDROID_LIBRARY_APPLY)
        pluginManager.apply(Dependencies.Plugins.KOTLIN_ANDROID_APPLY)
        pluginManager.apply(Dependencies.Plugins.KOTLIN_PARCELIZE_APPLY)
        pluginManager.apply(Dependencies.Plugins.KOTLIN_KAPT_APPLY)

        project.dependencies {
            coreLibraryDesugaring(Dependencies.Plugins.DESUGARING_PLUGIN)
        }

        project.extensions.configure<BaseExtension>("android") {
            compileSdkVersion(Versions.Android.compileSdkVersion)

            defaultConfig {
                minSdk = Versions.Android.minSdkVersion
                targetSdk = Versions.Android.targetSdkVersion
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                // only required for android modules dependent on appauth library directly
                // or modules dependent on sub-modules requiring appauth library (transitive)
                // but it is easier to handle it for all android modules here instead.
                // stands as long as :modules_core:database required with appauth dependency everywhere
                manifestPlaceholders["appAuthRedirectScheme"] = BuildType.APPLICATION_ID
            }

            buildTypes {
                getByName(BuildType.DEBUG).setIsDefault(true)
                getByName(BuildType.RELEASE)
            }

            compileOptions {
                isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = Versions.Java.sourceCompatibility
                targetCompatibility = Versions.Java.targetCompatibility
            }
        }

        project.tasks.withType(KotlinCompile::class.java) {
            kotlinOptions.jvmTarget = Versions.Kotlin.jvmTarget
        }

    }
}
