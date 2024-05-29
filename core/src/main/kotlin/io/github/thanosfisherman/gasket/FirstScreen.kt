package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.OrthographicCamera
import ktx.app.KtxInputAdapter
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.log.logger
import ktx.math.vec3

private val logger = logger<FirstScreen>()

class FirstScreen : KtxScreen {

    private val camera = OrthographicCamera().apply { setToOrtho(false, 800f, 800f) }
    private val vector = vec3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
    private var automaton = Automaton()

    override fun show() {
        Gdx.input.inputProcessor = object : KtxInputAdapter {
            override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
                automaton = Automaton()
                return true
            }
        }
    }

    override fun render(delta: Float) {
        clearScreen(red = 0f, green = 0f, blue = 0f)
        vector.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
        camera.unproject(vector)
        Gdx.graphics.setTitle("DEBUG - X: ${vector.x} Y: ${vector.y}")

        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit()

        automaton.draw()
    }

    override fun dispose() {

    }
}