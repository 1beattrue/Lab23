package ru.myitschool.lab23

import kotlin.math.pow

class MetricsData {
    private val metrics = MutableList(COEFFICIENTS.size) { START_VALUE }

    fun recalculate(index: Int, value: String): List<Double> {
        val valueDouble = value.toDoubleOrNull() ?: 0.0
        val valueMetres = convertToMetres(index, valueDouble)
        for (i in metrics.indices) {
            metrics[i] = valueMetres / COEFFICIENTS[i]
        }
        return metrics.toList()
    }

    private fun convertToMetres(index: Int, value: Double): Double {
        return value * COEFFICIENTS[index]
    }

    companion object {
        private const val START_VALUE = 0.0
        private val COEFFICIENTS = listOf(
            0.0254,
            0.9144,
            0.3048,
            1609.34,
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
    }
}
