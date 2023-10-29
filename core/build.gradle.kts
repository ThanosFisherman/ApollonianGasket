plugins {
    java
    kotlin("jvm")
}

group = "io.github.thanosfisherman.game"
version = "1.0-SNAPSHOT"

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

val assetsDir = rootProject.files("assets")
sourceSets {
    main {
        resources.srcDir(assetsDir)
    }
}