import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addCoreDependencies() {
    Dependencies.CoreDependency.getAll().forEach { api(it) }
}

fun DependencyHandler.addDesktopDependencies() {
    implementation(project(ModuleDependencies.CORE))
    Dependencies.DesktopDependency.getAll().forEach { implementation(it) }
}

fun DependencyHandler.addAndroidDependencies() {
    implementation(project(ModuleDependencies.CORE))
    Dependencies.DesktopDependency.getAll().forEach { implementation(it) }
}

fun DependencyHandler.addGraalDesktopDependencies() {
    implementation(project(ModuleDependencies.CORE))
    Dependencies.DesktopDependency.getAll().forEach { implementation(it) }
}

fun DependencyHandler.addGraalCoreDependencies() {
    implementation(project(ModuleDependencies.CORE))
    Dependencies.DesktopDependency.getAll().forEach { implementation(it) }
}