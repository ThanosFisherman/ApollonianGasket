plugins {
    java
    kotlin("jvm") version "1.9.0"
    id("com.android.application") version "8.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}

allprojects {
    group = "io.github.thanosfisherman.game"
    version = "1.0-SNAPSHOT"
}
