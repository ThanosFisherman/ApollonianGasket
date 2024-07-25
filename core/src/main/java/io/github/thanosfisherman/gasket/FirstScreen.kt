package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Pool
import com.badlogic.gdx.utils.viewport.FitViewport
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.app.KtxInputAdapter
import ktx.app.KtxScreen
import ktx.app.clearScreen
import ktx.assets.pool
import ktx.log.logger
import kotlin.math.absoluteValue

private val logger = logger<FirstScreen>()

class FirstScreen(private val game: Game) : KtxScreen {

    private val synth = game.realTimeSynth
    //private val camera = OrthographicCamera().apply { setToOrtho(false, 800f, 800f) }

    //private val vector = vec3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat())
    private val gameViewport = FitViewport(900f, 900f)
    private val uiViewport = ScreenViewport()

    private val batch = SpriteBatch()
    private val shape = ShapeRenderer()

    private val fps = FrameRate()
    private val gasketPool: Pool<Gasket> = pool(30) { Gasket(gameViewport.worldWidth, gameViewport.worldHeight) }
    lateinit var gasket: Gasket

    override fun show() {

        gasket = gasketPool.obtain()

        synth.createPolySynth()

        Gdx.input.inputProcessor = object : KtxInputAdapter {

            override fun keyUp(keycode: Int): Boolean {

                if (keycode == Keys.SPACE) {
                    gasketPool.free(gasket)
                    gasket = gasketPool.obtain()
                }
                if (keycode == Keys.F2) {
                    fps.isRendered = !fps.isRendered
                }
                return true
            }

            override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean {
                gasketPool.free(gasket)
                synth.start()
                gasket = gasketPool.obtain()
                return true
            }
        }
    }

    fun playSynth() {
        val now = synth.now
        synth.start()
        for (i in 0..16) {

            synth.play((55 * i).toString(), "8n", now + (i * 0.08f))
        }
    }

    override fun render(delta: Float) {
        clearScreen(red = 0f, green = 0f, blue = 0f)

        gasket.update(delta) { circles: List<Circle> ->
            if (synth.state == "running") {
                val now = synth.now
                logger.debug { "SIZE " + circles.size }
                circles.forEachIndexed { index, circle ->
                    val note = ((circle.bend * 10000) * (1..4).random()).absoluteValue
                    synth.play("%.3f".format(note), "16n", now + (index * 0.08f))
                }
            }
        }
        fps.update(delta)

//        vector.set(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
//        camera.unproject(vector)
//        Gdx.graphics.setTitle("DEBUG - X: ${vector.x} Y: ${vector.y}")

        if (Gdx.input.isKeyPressed(Keys.ESCAPE))
            Gdx.app.exit()

        Gdx.gl.glLineWidth(1.64f)

        gameViewport.apply()
        shape.projectionMatrix = gameViewport.camera.combined

        shape.begin(ShapeRenderer.ShapeType.Line)
        gasket.draw(shape)
        shape.end()

        uiViewport.apply()
        batch.projectionMatrix = uiViewport.camera.combined
        batch.begin()
        fps.render(batch, height = uiViewport.worldHeight)
        batch.end()
    }

    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
        uiViewport.update(width, height, true)
    }

    override fun dispose() {
        gasket.dispose()
        fps.dispose()
        shape.dispose()
        gasketPool.clear()
    }
}