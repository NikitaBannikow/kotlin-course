package practicalWork3

import kotlinx.serialization.Serializable

@Serializable
open class Rectangle(
    val sideA: Double,
    val sideB: Double
) : Shape {
    init {
        if (sideA <= 0.0 || sideB <= 0)
            throw IllegalArgumentException("Side shall have a positive length")
    }
    override fun calcArea(): Double {
        return sideA * sideB
    }

    override fun calcPerimeter(): Double {
        return (sideA + sideB) * 2
    }

    override fun toString(): String = "Rectangle[sideA:$sideA,sideB:$sideB]"

    override fun equals(other: Any?) = (other is Rectangle) && other.sideA == sideA && other.sideB == sideB
}
