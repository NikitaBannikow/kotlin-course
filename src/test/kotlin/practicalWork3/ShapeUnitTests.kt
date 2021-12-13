package practicalWork3

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class ShapeUnitTests {
    @Test
    internal fun circle() {
        val circle = Circle(15.0)
        assertEquals(706.85, circle.calcArea(), 0.2)
        assertEquals(94.24, circle.calcPerimeter(), 0.2)
    }

    @Test
    internal fun circle_factory() {
        val circle = ShapeFactoryImpl().createCircle(15.0)
        assertEquals(706.85, circle.calcArea(), 0.2)
        assertEquals(94.24, circle.calcPerimeter(), 0.2)
    }

    @Test
    internal fun err_circle() {
        var thrown = false

        try {
            Circle(-1.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun rectangle() {
        val rectangle = Rectangle(10.0, 15.0)
        assertEquals(150.0, rectangle.calcArea(), 0.1)
        assertEquals(50.0, rectangle.calcPerimeter(), 0.1)
    }

    @Test
    internal fun rectangle_factory() {
        val rectangle = ShapeFactoryImpl().createRectangle(10.0, 15.0)
        assertEquals(150.0, rectangle.calcArea(), 0.1)
        assertEquals(50.0, rectangle.calcPerimeter(), 0.1)
    }

    @Test
    internal fun err_rectangle_sideA() {
        var thrown = false

        try {
            Rectangle(-1.0, 10.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_rectangle_sideB() {
        var thrown = false

        try {
            Rectangle(1.0, 0.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun square() {
        val square = Square(10.0)
        assertEquals(100.0, square.calcArea(), 0.1)
        assertEquals(40.0, square.calcPerimeter(), 0.1)
    }

    @Test
    internal fun square_factory() {
        val square = ShapeFactoryImpl().createSquare(10.0)
        assertEquals(100.0, square.calcArea(), 0.1)
        assertEquals(40.0, square.calcPerimeter(), 0.1)
    }

    @Test
    internal fun err_square() {
        var thrown = false

        try {
            Square(-1.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_triangle1() {
        var thrown = false

        try {
            Triangle(-1.0, 1.0, 1.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_triangle2() {
        var thrown = false

        try {
            Triangle(1.0, -1.0, 1.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_triangle3() {
        var thrown = false

        try {
            Triangle(1.0, 1.0, -11.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_triangle4() {
        var thrown = false

        try {
            Triangle(1.0, 2.0, 10.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_triangle5() {
        var thrown = false

        try {
            Triangle(1.0, 210.0, 1.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_triangle6() {
        var thrown = false

        try {
            Triangle(10.0, 5.0, 1.0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun triangle() {
        val triangle = Triangle(10.0, 15.0, 10.0)
        assertEquals(49.6, triangle.calcArea(), 0.1)
        assertEquals(35.0, triangle.calcPerimeter(), 0.1)
    }

    @Test
    internal fun triangle_factory() {
        val triangle = ShapeFactoryImpl().createTriangle(10.0, 15.0, 10.0)
        assertEquals(49.6, triangle.calcArea(), 0.1)
        assertEquals(35.0, triangle.calcPerimeter(), 0.1)
    }

    @Test
    internal fun circle_factory_random() {
        repeat(10) {
            ShapeFactoryImpl().createRandomCircle()
        }
    }

    @Test
    internal fun rectangle_factory_random() {
        repeat(10) {
            ShapeFactoryImpl().createRandomRectangle()
        }
    }

    @Test
    internal fun square_factory_random() {
        repeat(10) {
            ShapeFactoryImpl().createRandomSquare()
        }
    }

    @Test
    internal fun triangle_factory_random() {
        repeat(100) {
            ShapeFactoryImpl().createRandomTriangle()
        }
    }

    @Test
    internal fun shape_factory_random() {
        repeat(100) {
            ShapeFactoryImpl().createRandomShape()
        }
    }
}
