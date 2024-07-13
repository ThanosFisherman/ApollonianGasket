package io.github.thanosfisherman.gasket

import com.badlogic.gdx.utils.Pool
import ktx.assets.invoke

object Descartes {

    // Calculate curvatures (k-values) for new circles using Descartes' theorem
    fun simple(c1: Circle, c2: Circle, c3: Circle): FloatArray {
        val k1 = c1.bend;
        val k2 = c2.bend;
        val k3 = c3.bend;
        // Sum and product of curvatures for Descartes' theorem
        val sum = k1 + k2 + k3;
        val product = kotlin.math.abs(k1 * k2 + k2 * k3 + k1 * k3);
        val root = 2 * kotlin.math.sqrt(product);
        val resultPlus = sum + root
        val resultMinus = sum - root
        return if (resultPlus == resultMinus) floatArrayOf(resultPlus) else floatArrayOf(resultPlus, resultMinus)
    }

    // Complex calculations based on Descartes' theorem for circle generation
// https://en.wikipedia.org/wiki/Descartes%27_theorem
    fun complex(c1: Circle, c2: Circle, c3: Circle, k4: FloatArray, pool: Pool<Circle>): Set<Circle> {
        // Curvature and center calculations for new circles
        val k1 = c1.bend;
        val k2 = c2.bend;
        val k3 = c3.bend;
        val z1 = c1.center;
        val z2 = c2.center;
        val z3 = c3.center;

        val zk1 = z1 * k1
        val zk2 = z2 * k2
        val zk3 = z3 * k3
        val sum = zk1 + zk2 + zk3

        val root = sqrt(zk1 * zk2 + zk2 * zk3 + zk1 * zk3) * 2

        val streamPlus =
            k4.map { it to (sum + root) * (1 / it) }.map { pair ->
                pool.obtain().also { circle: Circle -> circle.init(pair.second.real, pair.second.img, pair.first) }
            }
        val streamMinus =
            k4.map { it to (sum - root) * (1 / it) }.map { pair ->
                pool.obtain().also { circle: Circle -> circle.init(pair.second.real, pair.second.img, pair.first) }
            }

        return (streamPlus + streamMinus).toHashSet()
    }
}