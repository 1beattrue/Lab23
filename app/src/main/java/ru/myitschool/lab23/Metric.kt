package ru.myitschool.lab23

import kotlin.math.pow

sealed class Metric(
    open var value: Double,
    open val coefficient: Double
) {
    data class Inch(
        override var value: Double,
        override val coefficient: Double = 39.3701
    ) : Metric(value, coefficient)

    data class Yard(
        override var value: Double,
        override val coefficient: Double = 1.09361
    ) : Metric(value, coefficient)

    data class Foot(
        override var value: Double,
        override val coefficient: Double = 3.28084
    ) : Metric(value, coefficient)

    data class Mile(
        override var value: Double,
        override val coefficient: Double = 0.000_621_371
    ) : Metric(value, coefficient)

    data class Yottametre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(24)
    ) : Metric(value, coefficient)

    data class Zettametre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(21)
    ) : Metric(value, coefficient)

    data class Exametre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(18)
    ) : Metric(value, coefficient)

    data class Petametre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(15)
    ) : Metric(value, coefficient)

    data class Terrametre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(12)
    ) : Metric(value, coefficient)

    data class Gigametre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(9)
    ) : Metric(value, coefficient)

    data class Megametre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(6)
    ) : Metric(value, coefficient)

    data class Kilometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(3)
    ) : Metric(value, coefficient)

    data class Hectometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(2)
    ) : Metric(value, coefficient)

    data class Decametre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(1)
    ) : Metric(value, coefficient)

    data class Metre(
        override var value: Double,
        override val coefficient: Double = 1.0
    ) : Metric(value, coefficient)

    data class Decimetre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-1)
    ) : Metric(value, coefficient)

    data class Centimetre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-2)
    ) : Metric(value, coefficient)

    data class Millimetre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-3)
    ) : Metric(value, coefficient)

    data class Micrometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-6)
    ) : Metric(value, coefficient)

    data class Nanometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-9)
    ) : Metric(value, coefficient)

    data class Pikometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-12)
    ) : Metric(value, coefficient)

    data class Femtometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-15)
    ) : Metric(value, coefficient)

    data class Attometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-18)
    ) : Metric(value, coefficient)

    data class Zeptometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-21)
    ) : Metric(value, coefficient)

    data class Yoctometre(
        override var value: Double,
        override val coefficient: Double = 10.0.pow(-24)
    ) : Metric(value, coefficient)
}
