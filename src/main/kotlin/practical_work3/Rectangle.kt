package practical_work3

open class Rectangle(private val sideA: Double, private val sideB: Double) : Shape {
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
}
