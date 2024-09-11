package io.github.thanosfisherman.game.utils

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Pixmap
import com.badlogic.gdx.graphics.PixmapIO
import com.badlogic.gdx.graphics.glutils.FrameBuffer
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.viewport.ScreenViewport
import ktx.graphics.center
import ktx.graphics.use
import java.util.zip.Deflater

class Screenshot(
    private val shapeRenderer: ShapeRenderer,
    private val screenshotWidth: Int,
    private val screenshotHeight: Int
) {
    private var frameBuffer: FrameBuffer? = null

    fun captureHighRes(drawLogic: (shapeRenderer: ShapeRenderer) -> Unit) {
        // Create a FrameBuffer with the desired high resolution
        frameBuffer = FrameBuffer(
            Pixmap.Format.RGBA8888,
            screenshotWidth,
            screenshotHeight, false
        )

        val camera =
            OrthographicCamera(screenshotWidth.toFloat(), screenshotHeight.toFloat()).apply { setToOrtho(false) }
        val screenshotViewport = ScreenViewport(camera)

        screenshotViewport.update(screenshotWidth, screenshotHeight, true)
        screenshotViewport.apply(true)
        // Adjust the camera to cover the full resolution area
        camera.center()
        camera.update();
        // Begin rendering to the framebuffer
        frameBuffer?.begin()

        // Clear the framebuffer
//        clearScreen(red = 0.0f, green = 0.0f, blue = 0.0f)

        shapeRenderer.use(ShapeRenderer.ShapeType.Filled, screenshotViewport.camera.combined) {
            drawLogic.invoke(it)
        }
    }

    fun takeScreenshot() {
        val pixmap = Pixmap.createFromFrameBuffer(0, 0, screenshotWidth, screenshotHeight)
        val pixels = pixmap.pixels;

        // This loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
        val size = screenshotWidth * screenshotHeight * 4
        for (i in 3 until size step 4) {
            pixels.put(i, 255.toByte())
        }

        PixmapIO.writePNG(
            FileHandle("mypixmap${(Math.random() * 10000).toInt()}.png"),
            pixmap,
            Deflater.DEFAULT_COMPRESSION,
            true
        )
        frameBuffer?.end()
        pixmap.dispose()
        frameBuffer?.dispose()
    }
}