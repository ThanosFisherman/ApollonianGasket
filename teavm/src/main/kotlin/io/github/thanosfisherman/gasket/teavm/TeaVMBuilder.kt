@file:JvmName("TeaVMBuilder")
package io.github.thanosfisherman.gasket.teavm

import com.github.xpenatan.gdx.backends.teavm.config.AssetFileHandle
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuildConfiguration
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuilder
import java.io.File

/** Builds the TeaVM/HTML application.  */
fun main() {
    val teaBuildConfiguration = TeaBuildConfiguration()
    teaBuildConfiguration.assetsPath.add(AssetFileHandle("../assets"))
    teaBuildConfiguration.webappPath = File("build/dist").getCanonicalPath()

    // Register any extra classpath assets here:
    // teaBuildConfiguration.additionalAssetsClasspathFiles.add("io.github.thanosfisherman.gasket/asset.extension");

    // Register any classes or packages that require reflection here:
    // TeaReflectionSupplier.addReflectionClass("io.github.thanosfisherman.gasket.reflect");
    val tool = TeaBuilder.config(teaBuildConfiguration)
    tool.mainClass = TeaVMLauncher::class.java.getName()
    TeaBuilder.build(tool)
}
