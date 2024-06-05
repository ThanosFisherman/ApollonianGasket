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
    private val color: Color = Color.RED,
    private val camera: OrthographicCamera = OrthographicCamera()
) {

    init {
        camera.setToOrtho(false, 800f, 800f)
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
        Gdx.gl.glLineWidth(2f);
        shape.projectionMatrix = camera.combined
        shape.begin(ShapeRenderer.ShapeType.Line)
        shape.color = color
        shape.circle(x, y, radius, 200)
        shape.end()
    }

    fun distance(other: Circle): Float {
        return Vector2.dst(x, y, other.x, other.y)
    }
}