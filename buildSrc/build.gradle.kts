
plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
}

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
}

dependencies {

}

tasks.withType<Test>().configureEach {
    testLogging { showStandardStreams = true }
}

gradlePlugin {
}
