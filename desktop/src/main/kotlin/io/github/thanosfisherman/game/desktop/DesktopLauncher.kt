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
            setForegroundFPS(60)
        }

    Lwjgl3Application(Game(), config)
}