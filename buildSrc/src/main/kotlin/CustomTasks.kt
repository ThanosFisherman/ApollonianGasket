import org.gradle.api.Project
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Exec
import org.gradle.kotlin.dsl.register
import java.util.Properties

fun Project.registerDesktopTasks() {

//tasks.register<Jar>("jar") {
//    archiveBaseName.set("${project.property("name")}")
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//    from(files(sourceSets.main.get().output.classesDirs))
//    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
//
//    manifest {
//        attributes["Main-Class"] = application.mainClass.get()
//    }
//}


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