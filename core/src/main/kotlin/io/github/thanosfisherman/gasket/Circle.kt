package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.graphics.center

data class Circle(
    private val viewport: Viewport,
    var x: Float = 0f,
    var y: Float = 0f,
    var bend: Float = 0f,
    private var color: Color = Color.CYAN,
    private var segments: Int = 200,
    private var isCone: Boolean = false
) {

    var center = Complex(x, y)
    var radius = kotlin.math.abs(1 / bend)

    private val width = Gdx.graphics.width.toFloat()
    private val height = Gdx.graphics.height.toFloat()

    init {
        viewport.camera.center(width, height)
        shape.projectionMatrix = viewport.camera.combined
        Gdx.gl.glLineWidth(1.64f)
    }

    fun init(
        x: Float = 0f,
        y: Float = 0f,
        bend: Float = 0f,
        color: Color = Color.CYAN,
        segments: Int = 200,
        isCone: Boolean = false,
    ) {
        this.x = x
        this.y = y
        this.bend = bend
        this.color = color
        this.segments = segments
        this.isCone = isCone
        this.center = Complex(x, y)
        this.radius = kotlin.math.abs(1 / bend)
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


    fun draw(x: Float = this.center.real, y: Float = this.center.img, color: Color = this.color) {

        viewport.apply(true)
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