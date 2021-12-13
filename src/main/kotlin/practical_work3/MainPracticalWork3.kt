package practical_work3

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val factory = ShapeFactoryImpl()

    val shapes = listOf( // Shapes from Unit Tests
        factory.createCircle(15.0), // area: 706.85, perimeter: 94.24
        factory.createRectangle(10.0, 15.0), // area: 150.0, perimeter: 50.0
        factory.createSquare(10.0), // area: 100.0, perimeter: 40.0
        factory.createTriangle(10.0, 15.0, 10.0) // area: 49.6, perimeter: 35.0
    )

    dump("Shapes:", shapes)

    val randomShapes = mutableListOf<Shape>()
    repeat(10) {
        randomShapes.add(factory.createRandomShape())
    }
    dump("Random Shapes:", randomShapes)
}

fun dump(listName: String, shapes: List<Shape>) {
    println("List '$listName'")
    println("---------------------------------")

    val totalArea = shapes.sumOf { it.calcArea() }
    println("Total area size: $totalArea")
    val totalPerimeter = shapes.sumOf { it.calcPerimeter() }
    println("Total perimeter size: $totalPerimeter")

    val info =
        { shape: Shape -> shapeName(shape) + "(area:" + shape.calcArea() + ",perimeter:" + shape.calcPerimeter() + ")" }

    var shapeWithMinArea: Shape? = null
    var shapeWithMaxArea: Shape? = null
    var shapeWithMinPerimeter: Shape? = null
    var shapeWithMaxPerimeter: Shape? = null

    shapes.forEach { shape ->
        if (shapeWithMinArea == null || shape.calcArea() < shapeWithMinArea!!.calcArea()) shapeWithMinArea = shape
        if (shapeWithMaxArea == null || shape.calcArea() > shapeWithMaxArea!!.calcArea()) shapeWithMaxArea = shape
        if (shapeWithMinPerimeter == null || shape.calcPerimeter() < shapeWithMinPerimeter!!.calcPerimeter())
            shapeWithMinPerimeter = shape
        if (shapeWithMaxPerimeter == null || shape.calcPerimeter() > shapeWithMinPerimeter!!.calcPerimeter())
            shapeWithMaxPerimeter = shape
    }

    println("Shape with minimal area: ${info(shapeWithMinArea!!)}")
    println("Shape with maximum area: ${info(shapeWithMaxArea!!)}")
    println("Shape with minimal perimeter: ${info(shapeWithMinPerimeter!!)}")
    println("Shape with maximum perimeter: ${info(shapeWithMaxPerimeter!!)}")
    println()
}

fun shapeName(shape: Shape): String {
    return when (shape) {
        is Circle -> {
            "Circle"
        }
        is Square -> {
            "Square"
        }
        is Rectangle -> {
            "Rectangle"
        }
        is Triangle -> {
            "Triangle"
        }
        else -> "Unknown"
    }
}


