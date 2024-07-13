package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.utils.Pool
import ktx.app.KtxInputAdapter
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.pool

//private val logger = logger<FirstScreen>()

class FirstScreen : KtxScreen {

    //private val camera = OrthographicCamera().apply { setToOrtho(false, 800f, 800f) }

    //private val vector = vec3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
    private val fps = FrameRate()
    private val gasketPool: Pool<Gasket> = pool(30) { Gasket() }
    lateinit var gasket: Gasket

    override fun show() {

        gasket = gasketPool.obtain()

        Gdx.input.inputProcessor = object : KtxInputAdapter {

            override fun keyUp(keycode: Int): Boolean {

                if (keycode == Keys.SPACE) {
                    gasketPool.free(gasket)
                    gasket = gasketPool.obtain()
                    gasket.init()
                }
                if (keycode == Keys.F2) {
                    fps.isRendered = !fps.isRendered
                }
                return true
            }

            override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
                gasketPool.free(gasket)
                gasket = gasketPool.obtain()
                gasket.init()
                return true
            }
        }
    }

    override fun render(delta: Float) {
        clearScreen(red = 0f, green = 0f, blue = 0f)
        fps.update()
//        vector.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
//        camera.unproject(vector)
//        Gdx.graphics.setTitle("DEBUG - X: ${vector.x} Y: ${vector.y}")

        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit()

        gasket.draw()
        fps.render()
    }

    override fun resize(width: Int, height: Int) {
        fps.resize(width, height)
    }

    override fun dispose() {
        gasket.dispose()
        fps.dispose()
        gasketPool.clear()
    }
}