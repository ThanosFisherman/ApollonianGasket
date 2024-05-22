package io.github.thanosfisherman.gasket

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
        return floatArrayOf(sum + root, sum - root)
    }

    // Complex calculations based on Descartes' theorem for circle generation
// https://en.wikipedia.org/wiki/Descartes%27_theorem
    fun complex(c1: Circle, c2: Circle, c3: Circle, k4: FloatArray): Array<Circle> {
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

        var root = zk1 * zk2 + zk2 * zk3 + zk1 * zk3
        root = sqrt(root) * 2
        val center1 = (sum + root) * (1 / k4[0]);
        val center2 = (sum - root) * (1 / k4[0]);
        val center3 = (sum + root) * (1 / k4[1]);
        val center4 = (sum - root) * (1 / k4[1]);
        return arrayOf(
            Circle(center1.real, center1.img, k4[0]),
            Circle(center2.real, center2.img, k4[0]),
            Circle(center3.real, center3.img, k4[1]),
            Circle(center4.real, center4.img, k4[1]),
        )
    }
}