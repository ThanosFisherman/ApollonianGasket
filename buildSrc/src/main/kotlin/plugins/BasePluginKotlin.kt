package plugins

import Dependencies
import Versions
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

/**
 * Configures the module as a kotlin library.
 * Applies basic library and kotlin plugins and sets up build parameters.
 * Configures sonarqube for this module.
 */
class BasePluginKotlin : Plugin<Project> {
    override fun apply(project: Project) {
        val pluginManager = project.pluginManager
        pluginManager.apply(Dependencies.Plugins.JAVA_LIBRARY_APPLY)
        pluginManager.apply(Dependencies.Plugins.KOTLIN_JVM_APPLY)

        project.extensions.configure<JavaPluginExtension>("java") {
            toolchain {
                languageVersion.set(Versions.Java.javaToolchainVersion)
            }
//            sourceCompatibility = Versions.Java.sourceCompatibility
//            targetCompatibility = Versions.Java.targetCompatibility
        }

        project.extensions.configure<KotlinJvmProjectExtension>("kotlin") {
            jvmToolchain {
                languageVersion.set(Versions.Java.javaToolchainVersion)
            }
        }

//        project.tasks.withType(KotlinCompile::class.java) {
//            kotlinOptions.jvmTarget = Versions.Kotlin.jvmTarget
//        }
    }
}