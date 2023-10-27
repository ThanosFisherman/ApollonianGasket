plugins {
    java
    kotlin("jvm")
    application
}

group = "io.github.thanosfisherman.libgdxbootstrapper.desktop"
version = "1.0-SNAPSHOT"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("io.github.thanosfisherman.libgdxbootstraper.DesktopLuncherKt")
}


tasks {
    jar {
        from(files(sourceSets.main.get().output.classesDirs))
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })

        archiveBaseName.set("${project.property("name")}")

        manifest {
            attributes["Main-Class"] = application.mainClass.get()
        }
    }
}