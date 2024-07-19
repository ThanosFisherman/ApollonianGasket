import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.JavaExec
import org.gradle.api.tasks.bundling.Tar
import org.gradle.api.tasks.bundling.Zip
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.*
import java.io.File
import java.util.*

fun Project.registerDesktopTasks(mainClass: String) {

    tasks.getByName<JavaExec>("run") {
        workingDir = rootProject.file("assets")
        setIgnoreExitValue(true)

        // This next line could be needed to run LWJGL3 Java apps on macOS, but StartupHelper should make it unnecessary.
        // val os = System.getProperty("os.name").lowercase(Locale.getDefault())
        // if (os.contains("mac")) jvmArgs("-XstartOnFirstThread")
        // If you encounter issues with the 'lwjgl3:run' task on macOS specifically, try uncommenting the above line, and
        // regardless, please report it via the gdx-liftoff issue tracker or just mention it on the libGDX Discord.
    }

    tasks.getByName<Tar>("distTar") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
    tasks.getByName<Zip>("distZip") {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    tasks.getByName<Jar>("jar") {
        dependsOn(configurations.getByName("runtimeClasspath"))
        // sets the name of the .jar file this produces to the name of the game or app.
        archiveFileName.set(project.name + "-" + project.version + ".jar")
        val destDir = file(project.layout.buildDirectory.asFile.get().absolutePath + File.separator + "lib")
        // using 'lib' instead of the default 'libs' appears to be needed by jpackageimage.
        destinationDirectory.set(destDir)
        // the duplicatesStrategy matters starting in Gradle 7.0; this setting works.
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        println(configurations)

        from(configurations.get("runtimeClasspath").map { if (it.isDirectory) it else zipTree(it) })
        // these "exclude" lines remove some unnecessary duplicate files in the output JAR.
        excludes.apply {
            add("META-INF/INDEX.LIST")
            add("META-INF/*.SF")
            add("META-INF/*.DSA")
            add("META-INF/*.RSA")
        }
        dependencies {
            exclude("META-INF/INDEX.LIST", "META-INF/maven/**")
        }
        // setting the manifest makes the JAR runnable.
        manifest {
            attributes["Main-Class"] = mainClass
        }
        // this last step may help on some OSes that need extra instruction to make runnable JARs.
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