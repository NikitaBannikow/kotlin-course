package practicalWork3

import kotlinx.serialization.Serializable

@Serializable
class Circle (val radius : Double) : Shape {
    init {
        if (radius <= 0.0) {
            throw IllegalArgumentException("Radius shall have a positive value")
        }
    }

    override fun calcArea(): Double {
        return kotlin.math.PI * radius * radius
    }

    override fun calcPerimeter(): Double {
        return 2 * kotlin.math.PI * radius
    }

    override fun toString(): String = "Circle[radius:$radius]"

    override fun equals(other: Any?) = (other is Circle) && other.radius == radius
}
