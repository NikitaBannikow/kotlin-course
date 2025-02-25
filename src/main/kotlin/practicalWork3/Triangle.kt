package practicalWork3

import kotlinx.serialization.Serializable

@Serializable
class Triangle(val sideA: Double, val sideB: Double, val sideC: Double) : Shape {
    init {
        if (sideA <= 0.0 || sideB <= 0.0 || sideA <= 0.0)
            throw IllegalArgumentException("Side shall have a positive length")

        if (sideA + sideB <= sideC || sideB + sideC <= sideA || sideA + sideC <= sideB)
            throw IllegalArgumentException("Invalid triangle for provided sides")
    }
    override fun calcArea(): Double {
        val sm = calcPerimeter() / 2 // Semi Perimeter
        return kotlin.math.sqrt(sm * (sm - sideA) * (sm - sideB) * (sm - sideC))
    }

    override fun calcPerimeter(): Double = sideA + sideB + sideC

    override fun toString(): String = "Triangle[sideA:$sideA,sideB:$sideB,sideC:$sideC]"

    override fun equals(other: Any?) = (other is Triangle) && other.sideA == sideA
            && other.sideB == sideB && other.sideC == sideC
}
