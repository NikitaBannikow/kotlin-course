package practicalWork7

import org.junit.Test
import practicalWork3.Shape
import practicalWork3.Circle
import practicalWork3.Rectangle
import practicalWork3.Square
import practicalWork3.Triangle

class ShapeSerializationUnitTests {
    @Test
    internal fun `circle & square`() {
        val list = listOf(Circle(10.1), Square(11.0))
        val shapeSerialization = ShapeSerialization()
        val encoded = shapeSerialization.encode(list)
        val decoded = shapeSerialization.decode<List<Shape>>(encoded)

        assert(list == decoded)
    }

    @Test
    internal fun `rectangle & triangle`() {
        val list = listOf(Rectangle(10.0, 20.0), Triangle(11.0, 12.0, 13.0))
        val shapeSerialization = ShapeSerialization()
        val encoded = shapeSerialization.encode(list)
        val decoded = shapeSerialization.decode<List<Shape>>(encoded)

        assert(list == decoded)
    }
}
