import org.gradle.jvm.tasks.Jar

plugins {
    `base-plugin-kotlin`
    application
}

group = "io.github.thanosfisherman.game.desktop"
version = "1.0-SNAPSHOT"

dependencies {
    addDesktopDependencies()
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("io.github.thanosfisherman.game.desktop.DesktopLauncher")
}

registerDesktopTasks(application.mainClass.get())
