plugins {
    `base-plugin-kotlin`
    id(Dependencies.Plugins.GRETTY_APPLY) version Versions.Plugins.grettyVersion
}

group = "io.github.thanosfisherman.game.teavm"
version = "1.0-SNAPSHOT"

gretty {
    contextPath = "/"
    extraResourceBase("build/dist/webapp")
}

dependencies {
    addTeaVMDependencies()
}

val buildJavaScript = tasks.register<JavaExec>("buildJavaScript") {
    val mainClassName = "io.github.thanosfisherman.game.teavm.TeaVMBuilder"
    dependsOn(tasks.classes)
    description = "Transpile bytecode to JavaScript via TeaVM"
    mainClass.set(mainClassName)
    setClasspath(sourceSets.main.get().runtimeClasspath)
}

tasks.build.configure { dependsOn(buildJavaScript) }
val run = tasks.register("run") {
    description = "Run the JavaScript application hosted via a local Jetty server at http://localhost:8080/"
    dependsOn(buildJavaScript, ":teavm:jettyRun")
}
