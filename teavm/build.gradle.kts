plugins {
    java
    kotlin("jvm")
}

group = "io.github.thanosfisherman.game.teavm"
version = "1.0-SNAPSHOT"


dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

java {
    toolchain { languageVersion = JavaLanguageVersion.of(17) }
}
