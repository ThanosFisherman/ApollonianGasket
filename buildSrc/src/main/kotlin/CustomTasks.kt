import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Exec
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByName
import org.gradle.kotlin.dsl.register
import java.io.File
import java.util.*

fun Project.registerDesktopTasks(mainClass: String) {

    tasks.getByName<Jar>("jar") {
        archiveBaseName.set(project.name)
        val destDir = file(project.layout.buildDirectory.asFile.get().absolutePath + File.separator + "lib")
        destinationDirectory.set(destDir)
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        dependsOn(configurations.named("runtimeClasspath"))

        //from(files(sourceSets.main.get().output.classesDirs))
        from(configurations.named("runtimeClasspath").get().map { if (it.isDirectory) it else zipTree(it) })
        excludes.apply {
            add("META-INF/INDEX.LIST")
            add("META-INF/*.SF")
            add("META-INF/*.DSA")
            add("META-INF/*.RSA")
        }
        dependencies {
            exclude("META-INF/INDEX.LIST", "META-INF/maven/**")
        }
        manifest {
            attributes["Main-Class"] = mainClass
        }
        doLast {
            file(archiveFile).setExecutable(true, false)
        }
    }
}

fun Project.registerAndroidTasks() {

    tasks.register<Copy>("copyAndroidNatives") {

        file("libs/armeabi-v7a/").mkdirs()
        file("libs/arm64-v8a/").mkdirs()
        file("libs/x86_64/").mkdirs()
        file("libs/x86/").mkdirs()

        configurations.named("natives").orNull?.copy()?.files?.forEach { jar ->
            val outputDir = when {
                jar.name.endsWith("natives-armeabi-v7a.jar") -> {
                    file("libs/armeabi-v7a")
                }

                jar.name.endsWith("natives-arm64-v8a.jar") -> {
                    file("libs/arm64-v8a")
                }

                jar.name.endsWith("natives-x86_64.jar") -> {
                    file("libs/x86_64")
                }

                jar.name.endsWith("natives-x86.jar") -> {
                    file("libs/x86")
                }

                else -> {
                    null
                }
            }

            if (outputDir != null) {
                copy {
                    from(zipTree(jar))
                    into(outputDir)
                    include("*.so")
                }
            }
        }
    }

    tasks.matching { it.name.contains("merge") && it.name.contains("JniLibFolders") }
        .configureEach {
            dependsOn("copyAndroidNatives")
        }

    tasks.register<Exec>("run") {

        val localProperties = project.file("../local.properties")
        val path: String? = if (localProperties.exists()) {
            val properties = Properties()
            properties.load(localProperties.inputStream())
            properties.getProperty("sdk.dir") ?: System.getenv("ANDROID_SDK_ROOT")
        } else {
            System.getenv("ANDROID_SDK_ROOT")
        }

        val adb = "$path/platform-tools/adb"
        commandLine(
            adb,
            "shell",
            "am",
            "start",
            "-n",
            "com.badlogic.invaders/com.badlogic.invaders.android.AndroidLauncher"
        )
    }
}