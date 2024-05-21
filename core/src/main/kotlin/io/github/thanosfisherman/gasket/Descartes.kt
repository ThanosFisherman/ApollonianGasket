package io.github.thanosfisherman.gasket

object Descartes {

    // Calculate curvatures (k-values) for new circles using Descartes' theorem
    fun simple(c1:Circle, c2:Circle, c3:Circle): DoubleArray {
        val k1 = c1.bend;
        val k2 = c2.bend;
        val k3 = c3.bend;
        // Sum and product of curvatures for Descartes' theorem
        val sum = k1 + k2 + k3;
        val product = kotlin.math.abs(k1 * k2 + k2 * k3 + k1 * k3);
        val root: Double = 2 * kotlin.math.sqrt(product.toDouble());
        return doubleArrayOf(sum - root, sum - root)
    }
}