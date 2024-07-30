import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addCoreDependencies() {
    Dependencies.CoreDependency.getAll().forEach { api(it) }
}

fun DependencyHandler.addCoreKtxDependencies() {
    Dependencies.CoreKtxDependency.getAll().forEach { api(it) }
}

fun DependencyHandler.addDesktopDependencies() {
    implementation(project(ModuleDependencies.CORE))
    Dependencies.DesktopDependency.getAll().forEach { implementation(it) }
}

fun DependencyHandler.addAndroidDependencies() {
    implementation(project(ModuleDependencies.CORE))
    Dependencies.AndroidDependency.getAll().forEach { implementation(it) }
    Dependencies.AndroidNativesDependency.getAll().forEach { natives(it) }
}

fun DependencyHandler.addTeaVMDependencies() {
    implementation(project(ModuleDependencies.CORE))
    Dependencies.TeaVMDependency.getAll().forEach { implementation(it) }
}

fun DependencyHandler.addGraalDesktopDependencies() {
    Dependencies.GraalDesktop.getAll().forEach { implementation(it) }
}

fun DependencyHandler.addGraalCoreDependencies() {
    Dependencies.GraalCore.getAll().forEach { implementation(it) }
}