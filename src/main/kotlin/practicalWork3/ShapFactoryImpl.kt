package practicalWork3

val SHAPES_TYPES = setOf("Circle", "Rectangle", "Square", "Triangle")

class ShapeFactoryImpl : ShapeFactory {

    // Shapes factory
    override fun createCircle(radius: Double): Circle = Circle(radius)

    override fun createSquare(side: Double): Square = Square(side)

    override fun createRectangle(sideA: Double, sideB: Double): Rectangle = Rectangle(sideA, sideB)

    override fun createTriangle(sideA: Double, sideB: Double, sideC: Double): Triangle = Triangle(sideA, sideB, sideC)

    // Random shapes factory
    override fun createRandomCircle(): Circle = Circle(rnd())
    override fun createRandomSquare(): Square = Square(rnd())
    override fun createRandomRectangle(): Rectangle = Rectangle(rnd(), rnd())
    override fun createRandomTriangle(): Triangle {
        val sideA = rnd()
        val sideB = rnd()
        return Triangle(sideA, sideB, rnd(kotlin.math.abs(sideA - sideB), sideA + sideB))
    }

    override fun createRandomShape(): Shape {
        return when (SHAPES_TYPES.random()) {
            "Circle" -> createRandomCircle()
            "Rectangle" -> createRandomSquare()
            "Square" -> createRandomRectangle()
            else -> createRandomTriangle()
        }
    }

    private fun rnd(min: Double = 1.0, max: Double = 100.0) : Double {
        return kotlin.random.Random.nextDouble(min, max)
    }
}
