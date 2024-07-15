package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.log.logger

private val logger = logger<Game>()

class Game : KtxGame<KtxScreen>() {

    val viewport: Viewport = FitViewport(900f, 900f)

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        addScreen(FirstScreen(this))
        setScreen<FirstScreen>()
    }

    companion object {
        fun create() = Game()
    }
}

