package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.Disposable
import com.badlogic.gdx.utils.TimeUtils
import ktx.assets.disposeSafely


class FrameRate : Disposable {
    private var lastTimeCounted: Long
    var isRendered = false
    private var sinceChange = 0f
    private var frameRate: Float
    private val font: BitmapFont


    init {
        lastTimeCounted = TimeUtils.millis()
        frameRate = Gdx.graphics.framesPerSecond.toFloat()
        font = BitmapFont()
        font.region.texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest)
    }

    fun update(delta: Float) {
        if (!isRendered) return

        sinceChange += delta
        if (sinceChange >= 1f) {
            sinceChange = 0f
            frameRate = Gdx.graphics.framesPerSecond.toFloat()
        }
    }

    fun render(batch: SpriteBatch) {
        if (!isRendered) return

        font.draw(batch, frameRate.toInt().toString() + " fps", 0f, 16f)
    }

    override fun dispose() {
        font.disposeSafely()
    }
}