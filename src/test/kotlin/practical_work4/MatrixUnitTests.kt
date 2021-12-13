package practical_work4

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class MatrixUnitTests {
    @Test
    internal fun err_constructor_no_rows() {
        var thrown = false

        try {
            Matrix(emptyList())
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_constructor_empty_column() {
        var thrown = false

        try {
            val param = listOf(
                intArrayOf(),
                intArrayOf(1, 2, 3),
                intArrayOf(1, 1, 3),
                intArrayOf()
            )
            Matrix(param)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_constructor_not_equal_columns_sizes() {
        var thrown = false

        try {
            val param = listOf(
                intArrayOf(1, 2, 3),
                intArrayOf(1, 1, 3),
                intArrayOf(1, 5)
            )
            Matrix(param)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    private val first = Matrix(listOf (
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6)
    ))

    private val second = Matrix(listOf (
        intArrayOf(3, 2, 1),
        intArrayOf(1, 0, 5)
    ))

    @Test
    internal fun test_plus() {
        val expected = Matrix(listOf (
            intArrayOf(4, 4, 4),
            intArrayOf(5, 5, 11)
        ))
        assertEquals(expected, (first + second))
    }

    @Test
    internal fun test_equals_op() {
        val other = first.copy()
        val result : Boolean = other == first
        assert(result)
    }

    @Test
    internal fun test_plus_op() {
        var other = first.copy()

        other += second

        val expected = Matrix(listOf (
            intArrayOf(4, 4, 4),
            intArrayOf(5, 5, 11)
        ))
        assertEquals(expected, other)
    }

    @Test
    internal fun test_minus() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]

        val expected = Matrix(listOf (
            intArrayOf(-2, 0, 2),
            intArrayOf( 3, 5, 1)
        ))
        assertEquals(expected, (first - second))
    }

    @Test
    internal fun test_minus_op() {
        var other = first.copy()

        other -= second

        val expected = Matrix(listOf (
            intArrayOf(-2, 0, 2),
            intArrayOf( 3, 5, 1)
        ))
        assertEquals(expected, other)
    }

    @Test
    internal fun test_minus_scalar() {
        // first  = [1, 2, 3], [4, 5, 6]
        val other1 = first - 5
        var other2 = first.copy()
        other2 -= 5
        val expected = Matrix(listOf (
            intArrayOf(-4, -3, -2),
            intArrayOf(-1,  0,  1)
        ))
        assertEquals(expected, other1)
        assertEquals(expected, other2)
    }

    @Test
    internal fun test_times() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]

        val other1 = first * second
        var other2 = first.copy()
        other2 *= second

        val expected = Matrix(listOf (
            intArrayOf(3, 4, 3),
            intArrayOf(4, 0, 30)
        ))
        assertEquals(expected, other1)
        assertEquals(expected, other2)
    }

    @Test
    internal fun test_div() {
        // second = [3, 2, 1], [1, 0, 5]
        // first  = [1, 2, 3], [4, 5, 6]
        val other1 = second / first
        var other2 = second.copy()
        other2 /= first

        val expected = Matrix(listOf (
            intArrayOf(3, 1, 0),
            intArrayOf(0, 0, 0)
        ))
        assertEquals(expected, other1)
        assertEquals(expected, other2)
    }

    @Test
    internal fun test_div_scalar() {
        // second = [3, 2, 1], [1, 0, 5]
        val other1 = second / 2
        var other2 = second.copy()
        other2 /= 2

        val expected = Matrix(listOf (
            intArrayOf(1, 1, 0),
            intArrayOf(0, 0, 2)
        ))
        assertEquals(expected, other1)
        assertEquals(expected, other2)
    }

    @Test
    internal fun test_div_unary() {
        // second = [3, 2, 1], [1, 0, 5]
        val other1 = +second
        val other2 = -second

        val expected = Matrix(listOf (
            intArrayOf(-3, -2, -1),
            intArrayOf(-1, 0, -5)
        ))
        assertEquals(second, other1)
        assertEquals(expected, other2)
    }

    @Test
    internal fun err_test_div_unary() {
        // second = [3, 2, 1], [1, 0, 5]
        val result = MutableMatrix(second.copyList())
        result[0, 0] = 100
        result[1, 2] = 99
        val expected = Matrix(listOf (
            intArrayOf(100, 2, 1 ),
            intArrayOf(1,   0, 99)
        ))
        assertEquals(expected, result)
    }
}
