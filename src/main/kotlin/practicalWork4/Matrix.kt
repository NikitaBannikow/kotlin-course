package practicalWork4

import kotlin.text.StringBuilder as Text

/**
 * Implemented:
 * - matrix initialization
 * - getting dimensions of matrix
 * - getting element at position (row,column)
 * - method toString()
 * - Operations:
 *  - matrixA == matrixB
 *  - matrixA + matrixB
 *  - matrix + scalar
 *  - matrixA - matrixB
 *  - matrixA * matrixB
 *  - matrix * scalar
 *  - matrix / scalar
 *  - unary - and +
 * - Unit Tests
 */
open class Matrix(inputMatrix: Array<IntArray>) {
    protected var matrix = copyArray(inputMatrix)

    // Copy of the matrix
    fun copy() : Matrix = Matrix(matrix)

    // Copy of matrix data
    private fun copyArray(source: Array<IntArray>) : Array<IntArray> {
        if (source.isEmpty())
            throw IllegalArgumentException("Matrix rows shall not be empty.")

        val columnSize = source[0].size

        if (columnSize == 0)
            throw IllegalArgumentException("Matrix columns shall not be empty")

        source.forEachIndexed { rowIndex, columns ->
            if (columns.size != columnSize)
                throw IllegalArgumentException("Matrix columns sizes shall not be equal at row:$rowIndex")
        }

        val destination : Array<IntArray> = Array(source.size) { IntArray(source[0].size) {0}}
        for (row in source.indices)
            for (column in source[0].indices)
                destination[row][column] = source[row][column]
        return destination
    }

    fun toMutableMatrix() : MutableMatrix = MutableMatrix(matrix)

    val rowSize : Int
        get() = matrix.size

    val columnSize : Int
        get() = matrix[0].size

    // Supports: matrixA + matrixB
    operator fun plus(other: Matrix): Matrix {
        if (rowSize != other.rowSize || columnSize != other.columnSize)
            throw IllegalArgumentException("Dimensions are not equal")
        val copy = copy()
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy.matrix[row][column] = matrix[row][column] + other[row, column]
        return copy
    }

    // Supports: matrix + scalar
    operator fun plus(scalar: Int): Matrix {
        val copy = copy()
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy.matrix[row][column] = matrix[row][column] + scalar
        return copy
    }

    operator fun get(row: Int, column: Int): Int {
        if (row >= rowSize || row < 0)
            throw IndexOutOfBoundsException("Row index is out of bounds")
        if (column >= columnSize || column < 0)
            throw IndexOutOfBoundsException("Column index is out of bounds")
        return matrix[row][column]
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix) return false

        if (rowSize != other.rowSize || columnSize != other.columnSize) return false

        for (row in matrix.indices)
            for (column in matrix[row].indices)
                if (matrix[row][column] != other[row, column])
                    return false
        return true
    }

    // Supports: matrixA - matrixB
    operator fun minus(other: Matrix): Matrix {
        if (rowSize != other.rowSize || columnSize != other.columnSize)
            throw IllegalArgumentException("Dimensions are not equal")
        val copy = copy()
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy.matrix[row][column] = matrix[row][column] - other[row, column]
        return copy
    }

    // Supports: matrix - scalar
    operator fun minus(scalar: Int): Matrix {
        val copy = copy()
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy.matrix[row][column] = matrix[row][column] - scalar
        return copy
    }

    override fun toString(): String {
        val result = Text("[")
        for (row in 0 until rowSize) {
            result.append('[')
            for (column in 0 until columnSize) {
                result.append(this[row, column])
                if (column + 1 < columnSize) result.append(',')
            }
            result.append(']')

            if (row + 1 < rowSize) result.append(',')
        }
        return result.append(']').toString()
    }

    // Supports: matrixA * matrixB
    operator fun times(other: Matrix): Matrix {
        if (rowSize != other.rowSize || columnSize != other.columnSize)
            throw IllegalArgumentException("Dimensions are not equal")
        val copy = copy()
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy.matrix[row][column] = matrix[row][column] * other[row, column]
        return copy
    }

    // Supports: matrix * scalar
    operator fun times(scalar: Int): Matrix {
        val copy = copy()
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy.matrix[row][column] = matrix[row][column] * scalar
        return copy
    }

    // Supports: matrix / scalar
    operator fun div(scalar: Int): Matrix {
        val copy = copy()
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy.matrix[row][column] = matrix[row][column] / scalar
        return copy
    }

    override fun hashCode(): Int {
        return matrix.hashCode()
    }

    operator fun unaryMinus(): Matrix {
        val copy = copy()
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy.matrix[row][column] = -matrix[row][column]
        return copy
    }

    operator fun unaryPlus(): Matrix = this
}
