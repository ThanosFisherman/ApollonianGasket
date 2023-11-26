import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalModuleDependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.accessors.runtime.externalModuleDependencyFor
import org.gradle.kotlin.dsl.add

/*
 * These extensions mimic the extensions that are generated on the fly by Gradle.
 * They are used here to provide above dependency syntax that mimics Gradle Kotlin DSL
 * syntax in module\build.gradle.kts files.
 */

// region dependency extensions
fun DependencyHandler.implementation(
    dependencyNotation: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit,
): Dependency? = add("implementation", dependencyNotation, dependencyConfiguration)

fun DependencyHandler.implementation(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)

fun DependencyHandler.natives(dependencyNotation: Any): Dependency? =
    add("natives", dependencyNotation)

fun DependencyHandler.developmentImplementation(dependencyNotation: Any): Dependency? =
    add("developmentImplementation", dependencyNotation)

fun DependencyHandler.oemImplementation(dependencyNotation: Any): Dependency? =
    add("oemImplementation", dependencyNotation)

fun DependencyHandler.oemCNImplementation(dependencyNotation: Any): Dependency? =
    add("oemCNImplementation", dependencyNotation)

fun DependencyHandler.appStoreImplementation(dependencyNotation: Any): Dependency? =
    add("appStoreImplementation", dependencyNotation)

fun DependencyHandler.appStoreCNImplementation(dependencyNotation: Any): Dependency? =
    add("appStoreCNImplementation", dependencyNotation)

fun DependencyHandler.appStoreNAImplementation(dependencyNotation: Any): Dependency? =
    add("appStoreNAImplementation", dependencyNotation)

fun DependencyHandler.appStoreRUImplementation(dependencyNotation: Any): Dependency? =
    add("appStoreRUImplementation", dependencyNotation)

fun DependencyHandler.operationsImplementation(dependencyNotation: Any): Dependency? =
    add("operationsImplementation", dependencyNotation)

fun DependencyHandler.api(dependencyNotation: Any): Dependency? =
    add("api", dependencyNotation)

fun DependencyHandler.kapt(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.runtimeOnly(dependencyNotation: Any): Dependency? =
    add("runtimeOnly", dependencyNotation)

fun DependencyHandler.annotationProcessor(dependencyNotation: Any): Dependency? =
    add("annotationProcessor", dependencyNotation)

fun DependencyHandler.debugImplementation(dependencyNotation: Any): Dependency? =
    add("debugImplementation", dependencyNotation)

fun DependencyHandler.debugImplementation(
    dependencyNotation: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit,
): Dependency? = add("debugImplementation", dependencyNotation, dependencyConfiguration)

fun DependencyHandler.releaseImplementation(dependencyNotation: String): Dependency? =
    add("releaseImplementation", dependencyNotation)

fun DependencyHandler.releaseImplementation(
    dependencyNotation: String, dependencyConfiguration: ExternalModuleDependency.() -> Unit,
): Dependency? = add("releaseImplementation", dependencyNotation, dependencyConfiguration)

fun DependencyHandler.testImplementation(dependencyNotation: Any): Dependency? =
    add("testImplementation", dependencyNotation)

fun DependencyHandler.androidTestImplementation(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)

fun DependencyHandler.coreLibraryDesugaring(dependencyNotation: Any): Dependency? =
    add("coreLibraryDesugaring", dependencyNotation)

fun DependencyHandler.project(
    path: String, configuration: String? = null,
): ProjectDependency {
    val notation = if (configuration != null) {
        mapOf("path" to path, "configuration" to configuration)
    } else {
        mapOf("path" to path)
    }
    return uncheckedCast(project(notation))
}

@Suppress("unchecked_cast", "nothing_to_inline")
private inline fun <T> uncheckedCast(obj: Any?): T = obj as T



/**
 * Custom extension similar to [DependencyHandler.project] but for external library dependency files.
 *
 * This extension allows us to redefine a library file name (.jar or .aar) as a constant value in the build system
 * and add the dependency to other modules.
 *
 * Example with this extension:
 *      implementation(external("mabindings-core.jar")) // as string
 *      implementation(external(Dependencies.External.MaBindingsCore)) // as constant
 *
 * Example without this extension:
 *      implementation(group = "", name = "mabindings-core", ext = "jar")
 */
fun DependencyHandler.external(file: String, group: String = ""): ExternalModuleDependency {
    val name = file.substringBefore(".")
    val ext = file.substringAfter(".")

    return externalModuleDependencyFor(
        dependencyHandler = this,
        group = group,
        name = name,
        version = null,
        configuration = null,
        classifier = null,
        ext = ext
    )
}
