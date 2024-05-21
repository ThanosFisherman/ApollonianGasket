package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.log.logger
import ktx.math.vec3

private val logger = logger<FirstScreen>()

class FirstScreen : KtxScreen {

    private val camera = OrthographicCamera().apply { setToOrtho(false, 800f, 800f) }
    private val c1 = Circle(400f, 400f, -1 / 200f, camera)
    private val c2 = Circle(300f, 400f, 1 / 100f, camera)
    private val c3 = Circle(500f, 400f, 1 / 100f, camera)
    val bends4 = Descartes.simple(c1, c2, c3)
    val circlesNew = Descartes.complex(c1, c2, c3, bends4)
    private val vector = vec3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
    private val c4 = Circle(200f, 200f, bends4[0].toFloat(), camera)

    override fun render(delta: Float) {
        clearScreen(red = 0f, green = 0f, blue = 0f)
        vector.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
        camera.unproject(vector)
        Gdx.graphics.setTitle("DEBUG - X: ${vector.x} Y: ${vector.y}")
        c1.draw()
        c2.draw()
        c3.draw()
        //c4.draw(vector.x,vector.y, Color.CYAN)
        circlesNew[0].draw(color = Color.CYAN)
//        circlesNew[1].draw()
//        circlesNew[2].draw()
        circlesNew[3].draw(color = Color.CYAN)
        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit()

    }

    override fun dispose() {

    }
}