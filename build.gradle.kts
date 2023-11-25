plugins {
    //id("com.android.application") version Versions.Plugins.agpVersion apply false
    //id("com.android.library") version Versions.Plugins.agpVersion apply false
    //id("org.jetbrains.kotlin.android") version Versions.Plugins.kgpVersion apply false
    //id("org.jetbrains.kotlin.jvm") version Versions.Plugins.kgpVersion
    base
}

allprojects {
    group = "io.github.thanosfisherman.game"
    version = "1.0-SNAPSHOT"
}

tasks.getByName<Delete>("clean") {
    delete(rootProject.layout.buildDirectory)
}