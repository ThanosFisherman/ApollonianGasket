package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.assets.disposeSafely


class FrameRate : Disposable {
    private var lastTimeCounted: Long
    var isRendered = false
    private var sinceChange = 0f
    private var frameRate: Float
    private val font: BitmapFont
    private val batch: SpriteBatch
    private val viewport: Viewport = ScreenViewport()


    init {
        lastTimeCounted = TimeUtils.millis()
        frameRate = Gdx.graphics.framesPerSecond.toFloat()
        font = BitmapFont()
        font.region.texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
        batch = SpriteBatch()
    }

    fun resize(screenWidth: Int, screenHeight: Int) {
        viewport.update(screenWidth, screenHeight, true)
        batch.projectionMatrix = viewport.camera.combined
    }

    fun update() {
        if (!isRendered) return

        val delta: Long = TimeUtils.timeSinceMillis(lastTimeCounted)
        lastTimeCounted = TimeUtils.millis()

        sinceChange += delta.toFloat()
        if (sinceChange >= 1000) {
            sinceChange = 0f
            frameRate = Gdx.graphics.framesPerSecond.toFloat()
        }
    }

    fun render() {
        if (!isRendered) return

        viewport.apply(true)
        batch.begin()
        font.draw(batch, frameRate.toInt().toString() + " fps", 4f, viewport.worldHeight - 4f)
        batch.end()
    }

    override fun dispose() {
        font.disposeSafely()
        batch.disposeSafely()
    }
}