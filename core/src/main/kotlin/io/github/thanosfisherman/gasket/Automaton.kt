package io.github.thanosfisherman.gasket

import com.badlogic.gdx.graphics.Color
import kotlin.math.abs

class Automaton {
    private var c1 = Circle(400f, 400f, -1 / 200f)
    private val c2 = Circle(300f, 400f, 1 / 100f)
    private val c3 = Circle(500f, 400f, 1 / 100f)
    //private val triplet = Triplet(c1, c2, c3)

    fun draw() {

        nextGeneration()
    }

    fun nextGeneration() {

        c1.draw()
        c2.draw()
        c3.draw()

        val bends1 = Descartes.simple(c1, c2, c3)
        val circlesNew = Descartes.complex(c1, c2, c3, bends1)
        circlesNew[0].draw()

        val bends2 = Descartes.simple(c1, c2, circlesNew[0])
        val circlesNew2 = Descartes.complex(c1, c2, circlesNew[0], bends2)
        circlesNew2[0].draw(color = Color.GREEN)

        val bends3 = Descartes.simple(c1, circlesNew[0], circlesNew2[0])
        val circlesNew3 = Descartes.complex(c1, circlesNew[0], circlesNew2[0], bends3)
        circlesNew3[0].draw(color = Color.CYAN)

        val bends4 = Descartes.simple(circlesNew[0], circlesNew2[0], circlesNew3[0])
        val circlesNew4 = Descartes.complex(circlesNew[0], circlesNew2[0], circlesNew3[0], bends4)
        circlesNew4[0].draw(color = Color.CYAN)

        val bends5 = Descartes.simple(circlesNew2[0], circlesNew3[0], circlesNew4[0])
        val circlesNew5 = Descartes.complex(circlesNew2[0], circlesNew3[0], circlesNew4[0], bends5)
        circlesNew5[0].draw(color = Color.CYAN)

        val bends6 = Descartes.simple(circlesNew3[0], circlesNew4[0], circlesNew5[0])
        val circlesNew6 = Descartes.complex(circlesNew3[0], circlesNew4[0], circlesNew5[0], bends6)
        circlesNew6[0].draw(color = Color.CYAN)

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

data class Triplet(val first: Circle, val second: Circle, val third: Circle)
