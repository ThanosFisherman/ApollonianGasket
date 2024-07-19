@file:JvmName("TeaVMLauncher")

package io.github.thanosfisherman.gasket.teavm

import com.github.xpenatan.gdx.backends.teavm.TeaApplication
import com.github.xpenatan.gdx.backends.teavm.TeaApplicationConfiguration
import io.github.thanosfisherman.gasket.Game


/**
 * Launches the TeaVM/HTML application.
 * <br></br>
 * It may be important to note that if the TeaVM page is loaded from a URL with parameters,
 * that is, with a '?' sign after ".html" or some other file extension, then loading any
 * assets might not work right now. This is especially true when loading via IntelliJ IDEA's
 * built-in web server, which will default to adding on URL parameters that can be removed.
 */
object TeaVMLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        val config = TeaApplicationConfiguration("canvas").apply {
            antialiasing = true
            // change these to both 0 to use all available space, or both -1 for the canvas size.
            width = 0
            height = 0
        }

        TeaApplication(Game(ToneJs()), config)
    }
}
