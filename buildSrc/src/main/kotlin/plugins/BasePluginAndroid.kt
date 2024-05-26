package plugins

import BuildType
import Dependencies
import Versions
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import kotlin.collections.set

class BasePluginAndroid : Plugin<Project> {
    override fun apply(project: Project) {
        val pluginManager = project.pluginManager

        //pluginManager.apply(Dependencies.Plugins.ANDROID_LIBRARY_APPLY)
        pluginManager.apply(Dependencies.Plugins.KOTLIN_ANDROID_APPLY)
        pluginManager.apply(Dependencies.Plugins.ANDROID_APPLICATION_APPLY)
//        pluginManager.apply(Dependencies.Plugins.KOTLIN_PARCELIZE_APPLY)
        // pluginManager.apply(Dependencies.Plugins.KOTLIN_KAPT_APPLY)

        project.dependencies {
            //coreLibraryDesugaring(Dependencies.Plugins.DESUGARING_PLUGIN)
        }
        project.configurations.create("natives")
        project.extensions.configure<BaseAppModuleExtension>("android") {
            namespace = "io.github.thanosfisherman.game.android"
            compileSdk = Versions.Android.compileSdkVersion

            sourceSets {
                getByName("main") {
                    manifest.srcFile("src/main/AndroidManifest.xml")
                    java.srcDirs("src/main/java")
                    kotlin.srcDirs("src/main/kotlin")
                    aidl.srcDirs("src/main/java")
                    renderscript.srcDirs("src/main/java")
                    res.srcDirs("res")
                    assets.srcDirs("../assets")
                    jniLibs.srcDirs("libs")
                }

                getByName("test") {
                    setRoot("src/test")
                    assets.srcDirs("src/test/assets")
                }

                testOptions {
                    unitTests.isReturnDefaultValues = true
                    /**
                     * Gradle Daemons that is executing the tests on the build server has Java heap size set
                     * to 512MB by default, changing it to 4GB is equal to setup with local machine with 16GB
                     * and prevents the test to run into OutOfMemory Errors in tests
                     */
                    unitTests.all {
                        it.jvmArgs("-Xmx4g")
                    }
                }
            }

            defaultConfig {
                applicationId = "io.github.thanosfisherman.game.android"
                minSdk = 24
                targetSdk = 34
                versionCode = 1
                versionName = "1.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                vectorDrawables {
                    useSupportLibrary = true
                }

                // only required for android modules dependent on appauth library directly
                // or modules dependent on sub-modules requiring appauth library (transitive)
                // but it is easier to handle it for all android modules here instead.
                // stands as long as :modules_core:database required with appauth dependency everywhere
                manifestPlaceholders["appAuthRedirectScheme"] = BuildType.APPLICATION_ID
            }

            buildTypes {
                getByName(BuildType.DEBUG).isDefault = true
                getByName(BuildType.RELEASE) {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }

            compileOptions {
                // isCoreLibraryDesugaringEnabled = true
                sourceCompatibility = Versions.Java.sourceCompatibility
                targetCompatibility = Versions.Java.targetCompatibility
            }

            packaging {
                resources {
                    excludes.apply {
                        add("/META-INF/{AL2.0,LGPL2.1}")
                        add("META-INF/robovm/ios/robovm.xml")
                        add("META-INF/DEPENDENCIES.txt")
                        add("META-INF/DEPENDENCIES")
                        add("META-INF/dependencies.txt")
                        add("**/*.gwt.xml")
                    }
                    pickFirsts.apply {
                        add("META-INF/LICENSE.txt")
                        add("META-INF/LICENSE")
                        add("META-INF/license.txt")
                        add("META-INF/LGPL2.1")
                        add("META-INF/NOTICE.txt")
                        add("META-INF/NOTICE")
                        add("META-INF/notice.txt")
                    }
                }
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
        // To suppress the error about android not being able to find testClasses.
        // Might not be needed after all
        project.task("testClasses")
    }
}
