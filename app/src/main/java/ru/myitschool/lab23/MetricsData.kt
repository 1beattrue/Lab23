package ru.myitschool.lab23

import kotlin.math.pow

class MetricsData {
    private val coefficients = listOf(
        39.3701,
        1.09361,
        3.28084,
        0.000_621_371,
        10.0.pow(24),
        10.0.pow(21),
        10.0.pow(18),
        10.0.pow(15),
        10.0.pow(12),
        10.0.pow(9),
        10.0.pow(6),
        10.0.pow(3),
        10.0.pow(2),
        10.0.pow(1),
        1.0,
        10.0.pow(-1),
        10.0.pow(-2),
        10.0.pow(-3),
        10.0.pow(-6),
        10.0.pow(-9),
        10.0.pow(-12),
        10.0.pow(-15),
        10.0.pow(-18),
        10.0.pow(-21),
        10.0.pow(-24),
    )

    private val metrics: MutableList<Double> = mutableListOf<Double>().apply {
        repeat(25) {
            add(0.0)
        }
    }

    fun recalculate(index: Int, value: Double): List<Double> {
        val metres = convertToMetres(index, value)

        for (i in metrics.indices) {
            metrics[i] = metres / coefficients[i]
        }

        return metrics.toList()
    }

    private fun convertToMetres(index: Int, value: Double): Double {
        return value * coefficients[index]
    }
}
