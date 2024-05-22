package io.github.thanosfisherman.gasket

import com.badlogic.gdx.graphics.Color
import kotlin.math.abs

private const val epsilon = 0.1f

class Automaton {
    private var c1 = Circle(400f, 400f, -1 / 200f)
    private val c2 = Circle(300f, 400f, 1 / 100f)
    private val c3 = Circle(500f, 400f, 1 / 100f)
    private val allCircles = mutableListOf<Circle>().apply {
        add(c1)
        add(c2)
        add(c3)
    }
    private val queue = mutableListOf<Triplet>().apply {
        add(Triplet(c1, c2, c3))
    }

    fun draw() {

        for (c in allCircles) {
            c.draw()
        }
    }

    fun mouseClick() {
        val nextQueue = mutableListOf<Triplet>()
        for (triplet in queue) {
            val (c1, c2, c3) = triplet
            val k4 = Descartes.simple(c1, c2, c3)
            val r4 = abs(1 / k4[1])
            val newCircles = Descartes.complex(c1, c2, c3, k4)
            for (newCircle in newCircles) {
                allCircles.add(newCircle)
                val t1 = Triplet(c1, c2, newCircle)
                val t2 = Triplet(c1, c3, newCircle)
                val t3 = Triplet(c2, c3, newCircle)
                nextQueue.add(t1)
                nextQueue.add(t2)
                nextQueue.add(t3)
            }
        }
        queue.clear()
        queue.addAll(nextQueue)
    }

    fun nextGeneration() {

        c1.draw()
        c2.draw()
        c3.draw()

        val bends1 = Descartes.simple(c1, c2, c3)
        val circlesNew = Descartes.complex(c1, c2, c3, bends1)
        circlesNew[0].draw(color = Color.MAGENTA)
        circlesNew[1].draw(color = Color.MAGENTA)
        circlesNew[2].draw(color = Color.MAGENTA)
        circlesNew[3].draw(color = Color.MAGENTA)

        val bends2 = Descartes.simple(c1, c2, circlesNew[0])
        val circlesNew2 = Descartes.complex(c1, c2, circlesNew[0], bends2)
        circlesNew2[0].draw(color = Color.BLUE)
        circlesNew2[1].draw(color = Color.BLUE)
        circlesNew2[2].draw(color = Color.BLUE)
        circlesNew2[3].draw(color = Color.BLUE)

        val bends3 = Descartes.simple(c1, circlesNew[0], circlesNew2[0])
        val circlesNew3 = Descartes.complex(c1, circlesNew[0], circlesNew2[0], bends3)
        circlesNew3[0].draw(color = Color.CYAN)
        circlesNew3[1].draw(color = Color.CYAN)
        circlesNew3[2].draw(color = Color.CYAN)
        circlesNew3[3].draw(color = Color.CYAN)

        val bends4 = Descartes.simple(circlesNew[0], circlesNew2[0], circlesNew3[0])
        val circlesNew4 = Descartes.complex(circlesNew[0], circlesNew2[0], circlesNew3[0], bends4)
        circlesNew4[0].draw(color = Color.PURPLE)
        circlesNew4[1].draw(color = Color.PURPLE)
        circlesNew4[2].draw(color = Color.PURPLE)
        circlesNew4[3].draw(color = Color.PURPLE)

        val bends5 = Descartes.simple(circlesNew2[0], circlesNew3[0], circlesNew4[0])
        val circlesNew5 = Descartes.complex(circlesNew2[0], circlesNew3[0], circlesNew4[0], bends5)
        circlesNew5[0].draw(color = Color.FOREST)
        circlesNew5[1].draw(color = Color.FOREST)
        circlesNew5[2].draw(color = Color.FOREST)
        circlesNew5[3].draw(color = Color.FOREST)

        val bends6 = Descartes.simple(circlesNew3[0], circlesNew4[0], circlesNew5[0])
        val circlesNew6 = Descartes.complex(circlesNew3[0], circlesNew4[0], circlesNew5[0], bends6)
        circlesNew6[0].draw(color = Color.YELLOW)
        circlesNew6[1].draw(color = Color.YELLOW)
        circlesNew6[2].draw(color = Color.YELLOW)
        circlesNew6[3].draw(color = Color.YELLOW)

        val bends7 = Descartes.simple(circlesNew4[0], circlesNew5[0], circlesNew6[0])
        val circlesNew7 = Descartes.complex(circlesNew3[0], circlesNew4[0], circlesNew5[0], bends7)
        circlesNew7[0].draw(color = Color.GRAY)
        circlesNew7[1].draw(color = Color.GRAY)
        circlesNew7[2].draw(color = Color.GRAY)
        circlesNew7[3].draw(color = Color.GRAY)

    }

    // Determine if two circles are tangent to each other
    private fun isTangent(c1: Circle, c2: Circle): Boolean {
        val d = c1.distance(c2);
        val r1 = c1.radius;
        val r2 = c2.radius;
        // Tangency check based on distances and radii
        val a = abs(d - (r1 + r2)) < 0.1;
        val b = abs(d - abs(r2 - r1)) < 0.1;
        return a or b;
    }

    // Check if the potential new circle is valid
    private fun validate(c4: Circle, c1: Circle, c2: Circle, c3: Circle): Boolean {
        // Discards too small circles to avoid infinite recursion
        if (c4.radius < 2) return false;

        // Check if all 4 circles are mutually tangential
        if (!isTangent(c4, c1)) return false;
        if (!isTangent(c4, c2)) return false;
        if (!isTangent(c4, c3)) return false;

        return true;
    }
}

data class Triplet(val c1: Circle, val c2: Circle, val c3: Circle) {

}
