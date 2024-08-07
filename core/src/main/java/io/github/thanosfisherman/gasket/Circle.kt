package io.github.thanosfisherman.gasket

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.Vector2

data class Circle(
    var x: Float = 0f,
    var y: Float = 0f,
    var bend: Float = 0f,
    private var color: Color = Color.CYAN,
    private var segments: Int = 200,
    private var isCone: Boolean = false
) {

    var center = Complex(x, y)
    var radius = kotlin.math.abs(1 / bend)

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
        //println("BEND ${(bend)} RADIUS $radius")
    }

    fun config(color: Color, isCone: Boolean, segments: Int) {
        this.color = color
        this.segments = segments
        this.isCone = isCone
    }

    fun draw(shape: ShapeRenderer, x: Float = this.center.real, y: Float = this.center.img, color: Color = this.color) {

        shape.color = color

        if (isCone)
            shape.cone(x, y, 0f, radius, 0f, segments)
        else
            shape.circle(x, y, radius, 200)
    }

    fun distance(other: Circle): Float {
        return Vector2.dst(x, y, other.x, other.y)
    }
}