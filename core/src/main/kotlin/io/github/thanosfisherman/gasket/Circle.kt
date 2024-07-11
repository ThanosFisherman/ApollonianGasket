package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

data class Circle(
    var x: Float,
    var y: Float,
    val bend: Float,
    private var color: Color = Color.CYAN,
    private var segments: Int = 200,
    private var isCone: Boolean = false,
    private val camera: OrthographicCamera = OrthographicCamera()
) {

    private val width = Gdx.graphics.width.toFloat()
    private val height = Gdx.graphics.height.toFloat()

    init {
        camera.setToOrtho(false, width, height)
        Gdx.gl.glLineWidth(1.64f)
    }

    fun config(color: Color, isCone: Boolean, segments: Int) {
        this.color = color
        this.segments = segments
        this.isCone = isCone
    }

    companion object {
        private val shape = ShapeRenderer()

        fun dispose() {
            shape.dispose()
        }
    }

    val center = Complex(x, y)
    val radius = kotlin.math.abs(1 / bend)

    fun draw(x: Float = this.center.real, y: Float = this.center.img, color: Color = this.color) {
        shape.projectionMatrix = camera.combined
        shape.begin(ShapeRenderer.ShapeType.Line)
        shape.color = color

        if (isCone)
            shape.cone(x, y, 0f, radius, 0f, segments)
        else
            shape.circle(x, y, radius, 200)

        shape.end()
    }

    fun distance(other: Circle): Float {
        return Vector2.dst(x, y, other.x, other.y)
    }
}