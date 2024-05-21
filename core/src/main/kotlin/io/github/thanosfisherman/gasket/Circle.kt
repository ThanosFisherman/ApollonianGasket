package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

class Circle(var x: Float, var y: Float, val bend: Float, private val camera: OrthographicCamera) {

    private val shape = ShapeRenderer()
    val center = Complex(x, y)
    val radius = kotlin.math.abs(1 / bend)

    fun draw(x: Float = this.x, y: Float = this.y, color: Color = Color.RED) {
        Gdx.gl.glLineWidth(2f);
        shape.projectionMatrix = camera.combined
        shape.begin(ShapeRenderer.ShapeType.Line)
        shape.color = color
        shape.circle(x, y, radius,200)
        shape.end()
    }

    fun distance(other: Circle): Float {
        return Vector2.dst(x.toFloat(), y.toFloat(), other.x.toFloat(), other.y.toFloat())
    }
}