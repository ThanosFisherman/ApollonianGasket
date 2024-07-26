@file:JvmName("TeaVMBuilder")

package io.github.thanosfisherman.gasket.teavm

import com.github.xpenatan.gdx.backends.teavm.config.AssetFileHandle
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuildConfiguration
import com.github.xpenatan.gdx.backends.teavm.config.TeaBuilder
import org.teavm.vm.TeaVMOptimizationLevel
import java.io.File

/** Builds the TeaVM/HTML application.  */
fun main() {
    val teaBuildConfiguration = TeaBuildConfiguration()
    teaBuildConfiguration.assetsPath.add(AssetFileHandle("../assets"))
    teaBuildConfiguration.webappPath = File("build/dist").getCanonicalPath()
    teaBuildConfiguration.htmlTitle = "Apollonian Gasket by Thanos!"
    teaBuildConfiguration.htmlWidth = 900
    teaBuildConfiguration.htmlHeight = 900

    // Register any extra classpath assets here:
    // teaBuildConfiguration.additionalAssetsClasspathFiles.add("io.github.thanosfisherman.gasket/asset.extension");

    // Register any classes or packages that require reflection here:
    // TeaReflectionSupplier.addReflectionClass("io.github.thanosfisherman.gasket.reflect");
    val tool = TeaBuilder.config(teaBuildConfiguration)
    tool.setObfuscated(true)
    tool.optimizationLevel = TeaVMOptimizationLevel.ADVANCED
    tool.mainClass = TeaVMLauncher::class.java.getName()
    TeaBuilder.build(tool)
}
