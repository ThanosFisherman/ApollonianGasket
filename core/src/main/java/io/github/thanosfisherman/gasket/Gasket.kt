package io.github.thanosfisherman.gasket

import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.MathUtils.PI
import com.badlogic.gdx.math.RandomXS128
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Pool
import ktx.assets.pool
import ktx.collections.GdxArray
import ktx.log.logger
import kotlin.math.abs

// Tolerance for calculating tangency and overlap
private const val epsilon = 1f
private val logger = logger<Gasket>()

class Gasket(private val width: Float, private val height: Float) : Pool.Poolable {
    private var coneSegments = intArrayOf(4, 8, 20, 50).random()
    private var isConeShape = RandomXS128().nextInt(3) == 0
    private var colorRandomizer = ColorRandomizer()
    private var circlesPool: Pool<Circle> = pool(1200) { Circle() }
    private var sinceChange = 0f
    private var finishedCounter = 0

    // Initialize first circle centered on canvas
    // Second circle positioned randomly within the first
    // Third circle also positioned relative to the first
    private lateinit var c1: Circle;
    private lateinit var c2: Circle;
    private lateinit var c3: Circle

    private var r2 = 0f;
    private var r3 = 0f

    // Generate a random angle between 0 and 2*PI
    private var randomAngleRad: Float = 0f

    // Convert the angle to a unit vector
    private var v = Vector2()
    private var v2 = Vector2()

    // All circles in the gasket
    private var allCircles = GdxArray<Circle>(false, 64)

    // Queue for circles to process for next generation
    private var queue = GdxArray<Triplet>(false, 64)

    init {
        reset()
    }

    override fun reset() {
        finishedCounter = 0
        coneSegments = intArrayOf(4, 8, 20, 50).random()
        isConeShape = RandomXS128().nextInt(3) == 0
        colorRandomizer = ColorRandomizer()

        allCircles.forEach { circlesPool.free(it) }

        // Initialize first circle centered on canvas
        c1 = circlesPool.obtain().also {
            it.init(
                width / 2,
                height / 2,
                -1 / (width / 2),
                colorRandomizer.randomColor(),
                coneSegments,
                isConeShape
            )
        }

        r2 = randomFloatRange(100f, c1.radius / 2)

        // Generate a random angle between 0 and 2*PI
        randomAngleRad = randomFloatRange(0f, 2 * PI)

        // Convert the angle to a unit vector
        v.set(MathUtils.cos(randomAngleRad), MathUtils.sin(randomAngleRad))
            .setLength(c1.radius - r2)

        // Second circle positioned randomly within the first
        c2 = circlesPool.obtain().also {
            it.init(
                width / 2 + v.x,
                height / 2 + v.y,
                1 / r2,
                colorRandomizer.randomColor(),
                coneSegments,
                isConeShape
            )
        }

        r3 = v.len()
        v2.set(v).rotateRad(PI).setLength(c1.radius - r3)

        // Third circle also positioned relative to the first
        c3 = circlesPool.obtain().also {
            it.init(
                width / 2 + v2.x,
                height / 2 + v2.y,
                1 / r3,
                colorRandomizer.randomColor(),
                coneSegments,
                isConeShape
            )
        }

        // All circles in the gasket
        allCircles = GdxArray<Circle>(false, 64).apply {
            add(c1)
            add(c2)
            add(c3)
        }

        // Queue for circles to process for next generation
        queue = GdxArray<Triplet>(false, 64).apply {
            // Initial triplet for generating next generation of circles
            add(Triplet(c1, c2, c3))
        }
    }

    private fun isSimFinished(lenBefore: Int): Boolean {
        val lenAfter = allCircles.size
        return lenBefore == lenAfter
    }

    fun update(delta: Float, soundOn: (circles: List<Circle>) -> Unit) {
        sinceChange += delta
        if (finishedCounter >= 2)
            return
        if (finishedCounter >= 1) {
            val distinct: List<Circle> = allCircles.toList().filter { it.radius >= 10 }.distinctBy { it.radius }.shuffled()
            soundOn(distinct)
            finishedCounter++
            return
        }
        val lenBefore = allCircles.size
        if (sinceChange >= 0f) {
            sinceChange = 0f
            nextGeneration()
            if (isSimFinished(lenBefore)) {
                finishedCounter++
            }
        }
    }

    fun draw(shape: ShapeRenderer) {
        for (c in allCircles) {
            c.draw(shape)
        }
    }

    private fun nextGeneration() {
        val nextQueue = GdxArray<Triplet>(false, 16)
        for (triplet in queue) {
            val (c1, c2, c3) = triplet
            // Calculate curvature for the next circle
            val k4 = Descartes.simple(c1, c2, c3)
            // Generate new circles based on Complex Descartes' theorem
            val newCircles = Descartes.complex(c1, c2, c3, k4, circlesPool)
            for (newCircle in newCircles) {

                if (validate(newCircle, c1, c2, c3)) {
                    newCircle.config(colorRandomizer.randomColor(), isConeShape, coneSegments)
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
        circlesPool.clear()
        allCircles.clear()
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

