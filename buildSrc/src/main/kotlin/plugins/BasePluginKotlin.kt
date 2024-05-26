package plugins

import Dependencies
import Versions
import org.gradle.api.Action
import org.gradle.api.NamedDomainObjectProvider
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.named
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension
import java.io.File

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

        val assetsDir: File = project.rootProject.files("assets").singleFile
        project.sourceSets {
            main.configure {
                resources.srcDirs(assetsDir)
            }
        }
//        project.tasks.named("compileKotlin", org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask::class.java) {
//            compilerOptions {
//                freeCompilerArgs.add("-Xexport-kdoc")
//            }
//        }

        project.tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile>().configureEach {
            compilerOptions {
                languageVersion.set(org.jetbrains.kotlin.gradle.dsl.KotlinVersion.KOTLIN_2_0)
                jvmTarget.set(JvmTarget.fromTarget(Versions.Kotlin.jvmTarget))
            }
        }
    }
}

private val org.gradle.api.tasks.SourceSetContainer.main: NamedDomainObjectProvider<org.gradle.api.tasks.SourceSet>
    get() = named<org.gradle.api.tasks.SourceSet>("main")


private fun org.gradle.api.Project.sourceSets(configure: Action<org.gradle.api.tasks.SourceSetContainer>): Unit =
    (this as org.gradle.api.plugins.ExtensionAware).extensions.configure("sourceSets", configure)

