package io.github.thanosfisherman.gasket

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.MathUtils.PI
import com.badlogic.gdx.math.RandomXS128
import com.badlogic.gdx.math.Vector2
import ktx.math.vec2
import kotlin.math.abs

// Tolerance for calculating tangency and overlap
private const val epsilon = 1f
private val width = Gdx.graphics.width.toFloat()
private val height = Gdx.graphics.height.toFloat()

class Gasket {
    private val coneSegments = intArrayOf(4, 8, 20, 50).random()
    private val colorRandom =
        arrayOf(
            Color.BLUE,
            Color.CYAN,
            Color.MAGENTA,
            Color.VIOLET,
            Color.PURPLE,
            Color.PINK,
            Color.SALMON,
            Color.RED,
            Color.SCARLET,
            Color.CORAL,
            Color.FIREBRICK,
            Color.MAROON,
            Color.SKY,
            Color.TEAL,
            Color.NAVY,
            Color.SLATE,
            Color.ROYAL,
            Color.FOREST,
            Color.LIME,
            Color.CHARTREUSE,
            Color.GREEN,
            Color.OLIVE,
            Color.GRAY,
            Color.LIGHT_GRAY,
            Color.WHITE,
            Color.BROWN,
            Color.TAN,
            Color.YELLOW,
            Color.GOLD,
            Color.GOLDENROD,
            Color.ORANGE,
        ).random()
    private val isConeShape = RandomXS128().nextInt(3) == 0

    // Initialize first circle centered on canvas
    private var c1 = Circle(width / 2, height / 2, -1 / (width / 2), colorRandom, coneSegments, isConeShape)

    private val r2 = randomFloatRange(100f, c1.radius / 2)

    // Generate a random angle between 0 and 2*PI
    private val randomAngleRad: Float = randomFloatRange(0f, 2 * PI)

    // Convert the angle to a unit vector
    private val v = vec2(MathUtils.cos(randomAngleRad), MathUtils.sin(randomAngleRad))
        .setLength(c1.radius - r2)

    // Second circle positioned randomly within the first
    private val c2 = Circle(width / 2 + v.x, height / 2 + v.y, 1 / r2, colorRandom, coneSegments, isConeShape)

    private val r3 = v.len()
    private val v2 = Vector2(v).rotateRad(PI).setLength(c1.radius - r3)

    // Third circle also positioned relative to the first
    private val c3 = Circle(width / 2 + v2.x, height / 2 + v2.y, 1 / r3, colorRandom, coneSegments, isConeShape)

    // All circles in the gasket
    private val allCircles = mutableListOf<Circle>().apply {
        add(c1)
        add(c2)
        add(c3)
    }

    // Queue for circles to process for next generation
    private var queue = mutableListOf<Triplet>().apply {
        // Initial triplet for generating next generation of circles
        add(Triplet(c1, c2, c3))
    }


    fun draw() {

        nextGeneration()

        for (c in allCircles) {
            c.draw()
        }
    }

    fun nextGeneration() {
        val nextQueue = mutableListOf<Triplet>()
        for (triplet in queue) {
            val (c1, c2, c3) = triplet
            // Calculate curvature for the next circle
            val k4 = Descartes.simple(c1, c2, c3)
            // Generate new circles based on Complex Descartes' theorem
            val newCircles = Descartes.complex(c1, c2, c3, k4)
            for (newCircle in newCircles) {

                if (validate(newCircle, c1, c2, c3)) {
                    newCircle.config(colorRandom, isConeShape, coneSegments)
                    allCircles.add(newCircle)
                    // New triplets formed with the new circle for the next generation
                    val t1 = Triplet(c1, c2, newCircle)
                    val t2 = Triplet(c1, c3, newCircle)
                    val t3 = Triplet(c2, c3, newCircle)
                    nextQueue.add(t1)
                    nextQueue.add(t2)
                    nextQueue.add(t3)
                }
            }
        }
        queue.clear()
        queue.addAll(nextQueue)
    }

    fun dispose() {
        Circle.dispose()
    }

    // Determine if two circles are tangent to each other
    private fun isTangent(c1: Circle, c2: Circle): Boolean {
        val d = c1.distance(c2);
        val r1 = c1.radius;
        val r2 = c2.radius;
        // Tangency check based on distances and radii
        val a = abs(d - (r1 + r2)) < epsilon
        val b = abs(d - abs(r2 - r1)) < epsilon
        return a or b
    }

    // Check if the potential new circle is valid
    private fun validate(c4: Circle, c1: Circle, c2: Circle, c3: Circle): Boolean {
        // Discards too small circles to avoid infinite recursion
        if (c4.radius < 1) return false

        // Check if all 4 circles are mutually tangential
        if (!isTangent(c4, c1)) return false
        if (!isTangent(c4, c2)) return false
        if (!isTangent(c4, c3)) return false

        for (other in allCircles) {
            val d = c4.distance(other)
            val radiusDiff = abs(c4.radius - other.radius)
            // Ensures new circle doesn't overlap or is too close to existing circles
            if (d < epsilon && radiusDiff < epsilon) {
                return false
            }
        }

        return true
    }

    private fun randomFloatRange(min: Float, max: Float): Float {
        require(min < max) { "max must be greater than min" }
        val ran = RandomXS128()
        val result = min + ran.nextFloat() * (max - min)
        return result
    }
}

data class Triplet(val c1: Circle, val c2: Circle, val c3: Circle)

