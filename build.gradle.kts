plugins {
    id("com.android.application") version "8.1.2" apply false
    id("com.android.library") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    java
    base
}

allprojects {
    group = "io.github.thanosfisherman.game"
    version = "1.0-SNAPSHOT"
}

//tasks.register<Delete>("clean") {
//    delete(rootProject.layout.buildDirectory)
//}