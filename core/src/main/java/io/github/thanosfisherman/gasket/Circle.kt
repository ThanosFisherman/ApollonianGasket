package io.github.thanosfisherman.gasket

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
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

    val ran = MathUtils.random(1)
    fun draw(shape: ShapeRenderer, x: Float = this.center.real, y: Float = this.center.img, color: Color = this.color) {

        shape.color = color
        //shape.triangle(x, y, x + 15, y, x+15 - ((x + 15 - x) / 2), y + 15)


        if (ran < 1)
            drawUpTriangle(x, y, 16f, shape)
        else
            drawDownTriangle(x, y, 16f, shape)
    }

    fun distance(other: Circle): Float {
        return Vector2.dst(x, y, other.x, other.y)
    }


    // Function to draw an equilateral triangle
    private fun drawUpTriangle(x: Float, y: Float, sideLength: Float, shape: ShapeRenderer) {
        val h = (kotlin.math.sqrt(3f) / 2) * sideLength; // Height of the triangle

        // Calculate vertices
        val x1 = x;
        val y1 = y - h; // Top vertex (shifted upwards by height)
        val x2 = x - sideLength / 2;
        val y2 = y; // Bottom-left vertex
        val x3 = x + sideLength / 2;
        val y3 = y; // Bottom-right vertex

        // Draw the triangle
        shape.triangle(x1, y1, x2, y2, x3, y3);
    }

    private fun drawDownTriangle(x: Float, y: Float, sideLength: Float, shape: ShapeRenderer) {
        val h = (kotlin.math.sqrt(3f) / 2) * sideLength; // Height of the triangle

        // Calculate vertices
        val x1 = x;
        val y1 = y; // Bottom vertex
        val x2 = x - sideLength / 2;
        val y2 = y - h; // Top-left vertex (aligned with upward triangle's top vertex)
        val x3 = x + sideLength / 2;
        val y3 = y - h; // Top-right vertex

        shape.triangle(x1, y1, x2, y2, x3, y3);
    }
}