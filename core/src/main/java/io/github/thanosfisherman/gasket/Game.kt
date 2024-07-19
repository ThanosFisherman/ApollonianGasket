package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Application
import com.badlogic.gdx.Gdx
import ktx.app.KtxGame
import ktx.app.KtxScreen
import ktx.log.logger

private val logger = logger<Game>()

class Game(val synth: Synth) : KtxGame<KtxScreen>() {


    override fun create() {
        Gdx.app.logLevel = Application.LOG_DEBUG
        synth.message("OMG I CAN'T BELIEVE THIS SHIT WORKS")
        addScreen(FirstScreen(this))
        setScreen<FirstScreen>()
    }

}

