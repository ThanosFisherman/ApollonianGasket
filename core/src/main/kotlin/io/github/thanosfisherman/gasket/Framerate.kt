package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils


class FrameRate : Disposable {
    var lastTimeCounted: Long
    private var sinceChange = 0f
    private var frameRate: Float
    private val font: BitmapFont
    private val batch: SpriteBatch
    private var cam: OrthographicCamera


    init {
        lastTimeCounted = TimeUtils.millis()
        frameRate = Gdx.graphics.framesPerSecond.toFloat()
        font = BitmapFont()
        batch = SpriteBatch()
        cam = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    }

    fun resize(screenWidth: Int, screenHeight: Int) {
        cam = OrthographicCamera(screenWidth.toFloat(), screenHeight.toFloat())
        cam.translate((screenWidth / 2).toFloat(), (screenHeight / 2).toFloat())
        cam.update()
        batch.projectionMatrix = cam.combined
    }

    fun update() {
        val delta: Long = TimeUtils.timeSinceMillis(lastTimeCounted)
        lastTimeCounted = TimeUtils.millis()

        sinceChange += delta.toFloat()
        if (sinceChange >= 1000) {
            sinceChange = 0f
            frameRate = Gdx.graphics.framesPerSecond.toFloat()
        }
    }

    fun render() {
        batch.begin()
        font.draw(batch, frameRate.toInt().toString() + " fps", 3f, (Gdx.graphics.height - 3).toFloat())
        batch.end()
    }

    override fun dispose() {
        font.dispose()
        batch.dispose()
    }
}