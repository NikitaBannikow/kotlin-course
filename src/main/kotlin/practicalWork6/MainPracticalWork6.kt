package practicalWork6

import practicalWork3.Circle
import practicalWork3.Shape
import practicalWork3.ShapeFactoryImpl

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val factory = ShapeFactoryImpl()

    val shapes = listOf(
        // Shapes from Unit Tests
        factory.createCircle(15.0), // area: 706.85, perimeter: 94.24
        factory.createRectangle(10.0, 15.0), // area: 150.0, perimeter: 50.0
        factory.createSquare(10.0), // area: 100.0, perimeter: 40.0
        factory.createTriangle(10.0, 15.0, 10.0), // area: 49.6, perimeter: 35.0
        factory.createCircle(10.0), // area: 314.15, perimeter: 62.83
    )

    val shapeCollector = ShapeCollector<Shape>()
    shapeCollector.addAll(shapes)

    dump("Original shapes:", shapes)

    dump("Only circles:", shapeCollector.getAllByClass(Circle::class.java))

    dump(
        "Sorted by area in ascending order:",
        shapeCollector.getAllSorted(ShapeComparators.byAreaInAscending))
    dump(
        "Sorted by area in descending order:",
        shapeCollector.getAllSorted(ShapeComparators.byAreaInDescending))
    dump(
        "Sorted by perimeter in ascending order:",
        shapeCollector.getAllSorted(ShapeComparators.byPerimeterInAscending)
    )
    dump(
        "Sorted by perimeter in descending order:",
        shapeCollector.getAllSorted(ShapeComparators.byPerimeterInDescending)
    )

    val circleCollector = ShapeCollector<Circle>()
    circleCollector.add(Circle(15.0))
    circleCollector.add(Circle(10.0))
    circleCollector.add(Circle(12.0))

    dump(
        "Sorted circles by radius in ascending order:",
        circleCollector.getAllSorted(ShapeComparators.byRadiusInAscending)
    )

    dump(
        "Sorted circles by radius in descending order:",
        circleCollector.getAllSorted(ShapeComparators.byRadiusInDescending)
    )
}

fun dump(message: String, shapes: List<Shape>) {
    val info =
        { shape: Shape -> "$shape(area:" + shape.calcArea() + ",perimeter:" + shape.calcPerimeter() + ")" }

    println(message)
    println("-----------------------------------")
    shapes.forEach { shape ->
        println(info(shape))
    }
    println()
}
