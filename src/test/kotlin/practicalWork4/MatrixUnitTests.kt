package practicalWork4

import org.junit.Test

import kotlin.IllegalArgumentException
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotEquals

class MatrixUnitTests {

    @Test internal fun `empty matrix`() {
        assertFailsWith(IllegalArgumentException::class, block = {
            Matrix(emptyArray())
        })
    }

    @Test internal fun `empty columns`() {
        assertFailsWith(IllegalArgumentException::class, block = {
            val param = arrayOf(
                intArrayOf(),
                intArrayOf(1, 2, 3),
                intArrayOf(1, 1, 3),
                intArrayOf()
            )
            Matrix(param)
        })
    }

    @Test internal fun `different column sizes`() {
        assertFailsWith(IllegalArgumentException::class, block = {
            val param = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(1, 1, 3),
                intArrayOf(1, 5)
            )
            Matrix(param)
        })
    }

    private val first = Matrix(arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6)
    ))

    private val second = Matrix(arrayOf(
        intArrayOf(3, 2, 1),
        intArrayOf(1, 0, 5)
    ))

    @Test internal fun `get() negative row index`() {
        assertFailsWith(IndexOutOfBoundsException::class, block = {
            second[-1, 1]
        })
    }

    @Test internal fun `get() big row index`() {
        assertFailsWith(IndexOutOfBoundsException::class, block = {
            second[second.rowSize, 1]
        })
    }

    @Test internal fun `get() negative column index`() {
        assertFailsWith(IndexOutOfBoundsException::class, block = {
            second[1, -1]
        })
    }

    @Test internal fun `get() big column index`() {
        assertFailsWith(IndexOutOfBoundsException::class, block = {
            second[0, second.columnSize]
        })
    }

    @Test internal fun `matrixA plus matrixB`() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]
        val expected = Matrix(arrayOf (
            intArrayOf(4, 4, 4),
            intArrayOf(5, 5, 11)
        ))
        assertEquals(expected, (first + second))
    }

    @Test internal fun `copy matrix`() {
        val other = first.copy()
        val result : Boolean = other == first
        assert(result)
    }

    @Test internal fun `matrixA += matrixB`() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]

        var other = first.copy()
        other += second

        val expected = Matrix(arrayOf (
            intArrayOf(4, 4, 4),
            intArrayOf(5, 5, 11)
        ))
        assertEquals(expected, other)
    }

    @Test internal fun `matrixA - matrixB`() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]

        val expected = Matrix(arrayOf (
            intArrayOf(-2, 0, 2),
            intArrayOf( 3, 5, 1)
        ))
        assertEquals(expected, (first - second))
    }

    @Test
    internal fun `matrixA -= matrixB`() {
        var other = first.copy()

        other -= second

        val expected = Matrix(arrayOf (
            intArrayOf(-2, 0, 2),
            intArrayOf( 3, 5, 1)
        ))
        assertEquals(expected, other)
    }

    @Test internal fun `matrix - scalar`() {
        // first  = [1, 2, 3], [4, 5, 6]
        val other1 = first - 5
        var other2 = first.copy()
        other2 -= 5
        val expected = Matrix(arrayOf (
            intArrayOf(-4, -3, -2),
            intArrayOf(-1,  0,  1)
        ))
        assertEquals(expected, other1)
        assertEquals(expected, other2)
    }

    @Test internal fun `matrix times`() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]

        val other1 = first * second
        assertNotEquals(other1.hashCode(), first.hashCode())

        var other2 = first.copy()
        val other3 = other2
        other2 *= second
        assertNotEquals(other3.hashCode(), other2.hashCode())

        val expected = Matrix(arrayOf (
            intArrayOf(3, 4, 3),
            intArrayOf(4, 0, 30)
        ))
        assertEquals(expected, other1)
        assertEquals(expected, other2)
    }

    @Test internal fun `matrix div scalar`() {
        // second = [3, 2, 1], [1, 0, 5]
        val other1 = second / 2
        assertNotEquals(other1.hashCode(), second.hashCode())

        var other2 = second.copy()
        val other3 = other2
        other2 /= 2
        assertNotEquals(other3.hashCode(), other2.hashCode())

        val expected = Matrix(arrayOf (
            intArrayOf(1, 1, 0),
            intArrayOf(0, 0, 2)
        ))
        assertEquals(expected, other1)
        assertEquals(expected, other2)
    }

    @Test internal fun `unary + and -`() {
        // second = [3, 2, 1], [1, 0, 5]
        val other1 = +second
        val other2 = -second

        val expected = Matrix(arrayOf (
            intArrayOf(-3, -2, -1),
            intArrayOf(-1, 0, -5)
        ))
        assertEquals(second, other1)
        assertEquals(expected, other2)
    }
    @Test internal fun `set() negative row index`() {
        assertFailsWith(IndexOutOfBoundsException::class, block = {
            val matrix = second.toMutableMatrix()
            matrix[-1, 1] = 11
        })
    }

    @Test internal fun `set() big row index`() {
        assertFailsWith(IndexOutOfBoundsException::class, block = {
            val matrix = second.toMutableMatrix()
            matrix[matrix.rowSize, 1] = 11
        })
    }

    @Test internal fun `set() negative column index`() {
        assertFailsWith(IndexOutOfBoundsException::class, block = {
            val matrix = second.toMutableMatrix()
            matrix[0, -1] = 11
        })
    }

    @Test internal fun `set() big column index`() {
        assertFailsWith(IndexOutOfBoundsException::class, block = {
            val matrix = second.toMutableMatrix()
            matrix[0, matrix.columnSize] = 11
        })
    }

    @Test internal fun `set element`() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]

        val matrix = second.toMutableMatrix()
        matrix[0, 0] = 100
        matrix[1, 2] = 99

        val expected = Matrix(arrayOf (
            intArrayOf(100, 2, 1 ),
            intArrayOf(1,   0, 99)
        ))
        assertEquals(expected, matrix)
    }

    @Test internal fun `plusAssign scalar`() {
        val matrix = MutableMatrix(arrayOf(
            intArrayOf(3, 2, 1),
            intArrayOf(1, 0, 5)
        ))
        matrix += 10
        val expected = Matrix(arrayOf (
            intArrayOf(13, 12, 11),
            intArrayOf(11, 10, 15)
        ))
        assertEquals(expected, matrix)
    }

    @Test internal fun `plusAssign matrix`() {
        val matrixA = MutableMatrix(arrayOf(
            intArrayOf(3, 2, 1),
            intArrayOf(1, 0, 5)
        ))

        val matrixB = MutableMatrix(arrayOf(
            intArrayOf(3, 2, 1),
            intArrayOf(1, 0, 5)
        ))

        matrixA += matrixB
        val expected = Matrix(arrayOf (
            intArrayOf(6, 4, 2),
            intArrayOf(2, 0, 10)
        ))
        assertEquals(expected, matrixA)
    }

    @Test internal fun `minusAssign scalar`() {
        val matrix = MutableMatrix(arrayOf(
            intArrayOf(13, 12, 11),
            intArrayOf(11, 10, 15)
        ))

        matrix -= 10

        val expected = Matrix(arrayOf (
            intArrayOf(3, 2, 1),
            intArrayOf(1, 0, 5)
        ))
        assertEquals(expected, matrix)
    }

    @Test internal fun `minusAssign matrix`() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]
        val matrixA = first.toMutableMatrix()
        val matrixB = second.toMutableMatrix()

        matrixA -= matrixB
        val expected = Matrix(arrayOf (
            intArrayOf(-2, 0, 2),
            intArrayOf( 3, 5, 1)
        ))
        assertEquals(expected, matrixA)
    }

    @Test internal fun `mutable matrix times scalar`() {
        // second = [3, 2, 1], [1, 0, 5]
        val matrix = second.toMutableMatrix()
        matrix *= 10
        val expected = Matrix(arrayOf (
            intArrayOf(30, 20, 10 ),
            intArrayOf(10,  0, 50)
        ))
        assertEquals(expected, matrix)
    }

    @Test internal fun `mutable matrix times matrix`() {
        // first  = [1, 2, 3], [4, 5, 6]
        // second = [3, 2, 1], [1, 0, 5]
        val matrixA = first.toMutableMatrix()
        val matrixB = second.toMutableMatrix()

        matrixA *= matrixB
        val expected = Matrix(arrayOf (
            intArrayOf(3, 4, 3),
            intArrayOf(4, 0, 30)
        ))
        assertEquals(expected, matrixA)
    }

    @Test internal fun `mutable matrix div scalar`() {
        val matrix = MutableMatrix(arrayOf(
            intArrayOf(12, 30, 24),
            intArrayOf(3,  0, 15)
        ))

        matrix /= 3

        val expected = Matrix(arrayOf (
            intArrayOf(4, 10, 8),
            intArrayOf(1,  0, 5)
        ))
        assertEquals(expected, matrix)
    }
}
