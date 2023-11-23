import org.gradle.api.invocation.Gradle

interface BuildType {

    companion object {
        const val RELEASE = "release"
        const val DEBUG = "debug"
        const val APPLICATION_ID = "io.github.thanosfisherman.game"
    }

    val isMinifyEnabled: Boolean
    val isShrinkResources: Boolean
    val manifestPlaceholders: Map<String, String>
    val isDebuggable: Boolean
}

object BuildTypeDebug : BuildType {
    override val isMinifyEnabled = false
    override val isShrinkResources = false
    override val isDebuggable = true
    override val manifestPlaceholders = mapOf<String, String>()
}

object BuildTypeRelease : BuildType {
    override val isMinifyEnabled = true
    override val isShrinkResources = true
    override val isDebuggable = false
    override val manifestPlaceholders = mapOf<String, String>()
}

fun Gradle.isReleaseBuild() = this.startParameter.taskNames.any { it.contains(BuildType.RELEASE) }
