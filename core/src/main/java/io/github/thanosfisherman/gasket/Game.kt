package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.log.logger

private val logger = logger<Game>()

class Game(val realTimeSynth: RealTimeSynth) : KtxGame<KtxScreen>() {

    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        realTimeSynth.loadLibrary()
        addScreen(FirstScreen(this))
        setScreen<FirstScreen>()
    }
}

