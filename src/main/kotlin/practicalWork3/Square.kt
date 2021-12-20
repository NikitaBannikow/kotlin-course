package practicalWork3

import kotlinx.serialization.Serializable

@Serializable
class Square (val side: Double): Shape {
    init {
        if (side <= 0) {
            throw IllegalArgumentException("Side shall have a positive value")
        }
    }

    override fun calcArea(): Double = side * side

    override fun calcPerimeter(): Double = 4 * side

    override fun toString(): String = "Square[side:$side]"

    override fun equals(other: Any?) = (other is Square) && other.side == side
}
