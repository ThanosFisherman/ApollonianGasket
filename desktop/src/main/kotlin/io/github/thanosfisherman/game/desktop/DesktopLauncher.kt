@file:JvmName("DesktopLauncher")

package io.github.thanosfisherman.game.desktop

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import io.github.thanosfisherman.game.Game

fun main() {
    // This handles macOS support and helps on Windows.
    if (StartupHelper.startNewJvmIfRequired())
        return

    val config =
        Lwjgl3ApplicationConfiguration().apply {
            val displayMode = Lwjgl3ApplicationConfiguration.getDisplayMode()
            val width = displayMode.width * 0.75f
            val height = width / 16f * 9f
            setWindowedMode(width.toInt(), height.toInt())
            setWindowIcon(*(arrayOf(128, 64, 32, 16).map { "libgdx$it.png" }.toTypedArray()))
            setTitle("Game")
            useVsync(true)
            //// Limits FPS to the refresh rate of the currently active monitor.
            setForegroundFPS(Lwjgl3ApplicationConfiguration.getDisplayMode().refreshRate)
            //// If you remove the above line and set Vsync to false, you can get unlimited FPS, which can be
            //// useful for testing performance, but can also be very stressful to some hardware.
            //// You may also need to configure GPU drivers to fully disable Vsync; this can cause screen tearing.
            setBackBufferConfig(8,8,8,8,16,8,4)
        }

    Lwjgl3Application(Game(), config)
}