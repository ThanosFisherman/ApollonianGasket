package io.github.thanosfisherman.game

import ktx.app.KtxGame
import ktx.app.KtxScreen

class Game : KtxGame<KtxScreen>() {

    companion object {
        fun create() = Game()
    }
}