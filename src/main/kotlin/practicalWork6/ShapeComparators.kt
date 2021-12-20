package practicalWork6

import practicalWork3.Shape
import practicalWork3.Circle

object ShapeComparators {
    // For sorting shapes by area
    val byAreaInAscending = compareBy<Shape> { it.calcArea() }
    val byAreaInDescending = compareByDescending<Shape> { it.calcArea() }

    // For sorting shapes by perimeter
    val byPerimeterInAscending = compareBy<Shape> { it.calcPerimeter() }
    val byPerimeterInDescending = compareByDescending<Shape> { it.calcPerimeter() }

    // For sorting circles by radius
    val byRadiusInAscending = compareBy<Circle> { it.radius }
    val byRadiusInDescending = compareByDescending<Circle> { it.radius }
}
