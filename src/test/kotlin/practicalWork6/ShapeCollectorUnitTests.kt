package practicalWork6

import org.junit.Test
import practicalWork3.*
import org.junit.Assert.assertEquals

class ShapeCollectorUnitTests {
    private val circle1 = Circle(15.0) // area: 706.85, perimeter: 94.24
    private val rectangle = Rectangle(10.0, 15.0) // area: 150.0, perimeter: 50.0
    private val square = Square(10.0) // area: 100.0, perimeter: 40.0
    private val triangle = Triangle(10.0, 15.0, 10.0) // area: 49.6, perimeter: 35.0
    private val circle2 = Circle(10.0) // area: 314.15, perimeter: 62.83

    @Test
    internal fun `add to collector`() {
        val shapes = listOf(circle1, rectangle, square, triangle, circle2)

        val shapeCollector = ShapeCollector<Shape>()

        shapes.forEach { shape -> shapeCollector.add(shape) }

        assert((shapes.containsAll(shapeCollector.getAll()) && shapeCollector.getAll().containsAll(shapes)))
    }

    @Test
    internal fun `addAll to collector`() {
        val shapes = listOf(circle1, rectangle, square, triangle, circle2)

        val shapeCollector = ShapeCollector<Shape>()

        shapeCollector.addAll(shapes)

        assert((shapes.containsAll(shapeCollector.getAll()) && shapeCollector.getAll().containsAll(shapes)))
    }

    @Test
    internal fun `getAllByClass from collector`() {
        val shapeCollector = ShapeCollector<Shape>()
        shapeCollector.addAll(listOf(circle1, rectangle, square, triangle, circle2))
        val expected = listOf(circle1, circle2)
        val circles = shapeCollector.getAllByClass(Circle::class.java)

        assert((circles.containsAll(expected) && expected.containsAll(circles)))
    }

    @Test
    internal fun `getAllSorted by area in ascending order`() {
        val shapeCollector = ShapeCollector<Shape>()
        shapeCollector.addAll(listOf(circle1, rectangle, square, triangle, circle2))
        val expected = listOf(triangle, square, rectangle, circle2, circle1)
        val sorted = shapeCollector.getAllSorted(ShapeComparators.byAreaInAscending)

        assertEquals(expected, sorted)
    }

    @Test
    internal fun `getAllSorted by area in descending order`() {
        val shapeCollector = ShapeCollector<Shape>()
        shapeCollector.addAll(listOf(circle1, rectangle, square, triangle, circle2))
        val expected = listOf(triangle, square, rectangle, circle2, circle1).reversed()
        val sorted = shapeCollector.getAllSorted(ShapeComparators.byAreaInDescending)

        assertEquals(expected, sorted)
    }

    @Test
    internal fun `getAllSorted by perimeter in ascending order`() {
        val shapeCollector = ShapeCollector<Shape>()
        shapeCollector.addAll(listOf(circle1, rectangle, triangle, circle2, square))
        val expected = listOf(triangle, square, rectangle, circle2, circle1)
        val sorted = shapeCollector.getAllSorted(ShapeComparators.byPerimeterInAscending)

        assertEquals(expected, sorted)
    }

    @Test
    internal fun `getAllSorted by perimeter in descending order`() {
        val shapeCollector = ShapeCollector<Shape>()
        shapeCollector.addAll(listOf(circle1, rectangle, triangle, circle2, square))
        val expected = listOf(triangle, square, rectangle, circle2, circle1).reversed()
        val sorted = shapeCollector.getAllSorted(ShapeComparators.byPerimeterInDescending)

        assertEquals(expected, sorted)
    }

    @Test
    internal fun `getAllSorted by radius in ascending order`() {
        val shapeCollector = ShapeCollector<Circle>()
        shapeCollector.addAll(listOf(circle1, circle2))
        val expected = listOf(circle2, circle1)
        val sorted = shapeCollector.getAllSorted(ShapeComparators.byRadiusInAscending)

        assertEquals(expected, sorted)
    }

    @Test
    internal fun `getAllSorted by radius in descending order`() {
        val shapeCollector = ShapeCollector<Circle>()
        shapeCollector.addAll(listOf(circle2, circle1))
        val expected = listOf(circle1, circle2)
        val sorted = shapeCollector.getAllSorted(ShapeComparators.byRadiusInDescending)

        assertEquals(expected, sorted)
    }
}
