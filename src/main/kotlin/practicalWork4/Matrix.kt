package practicalWork4

import kotlin.text.StringBuilder as Text

/**
 * Implemented:
 * - matrix initialization
 * - getting dimensions of matrix
 * - getting element at position (i,j)
 * - method toString()
 * - Operations:
 *  - matrixA == matrixB
 * - Operations (new matrix):
 *  - matrixA + matrixB, matrixA += matrixB
 *  - matrix + scalar, matrix += scalar
 *  - matrixA - matrixB, matrixA -= matrixB
 *  - matrixA * matrixB, matrixA *= matrixB
 *  - matrix * scalar, matrix *= scalar
 *  - matrixA / matrixB, matrixA /= matrixB
 *  - matrix / scalar, matrix = scalar
 *  - unary - and +
 * - change element via operator [ ] (new matrix generated)
 * - Unit Tests
 */
open class Matrix(inputMatrix: List<IntArray>) {
    protected var matrix = listOf(intArrayOf())

    init {
        if (inputMatrix.isEmpty())
            throw IllegalArgumentException("Matrix rows shall not be empty.")

        val columnSize = inputMatrix[0].size

        if (columnSize == 0)
            throw IllegalArgumentException("Matrix columns shall not be empty")

        inputMatrix.forEachIndexed { rowIndex, columns ->
            if (columns.size != columnSize)
                throw IllegalArgumentException("Matrix columns sizes shall not be equal at row:$rowIndex")
        }

        matrix = inputMatrix.toList()
    }

    fun rowSize() : Int {
        return matrix.size
    }

    fun columnSize() : Int {
        return matrix[0].size
    }

    // Supports: matrixA + matrixB, matrixA += MatrixB
    operator fun plus(other: Matrix): Matrix {
        if (rowSize() != other.rowSize() || columnSize() != other.columnSize())
            throw IllegalArgumentException("Dimensions are not equal")
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column] + other[row, column]
        return Matrix(copy)
    }

    // Supports: matrix + scalar, matrix += scalar
    operator fun plus(scalar: Int): Matrix {
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column] + scalar
        return Matrix(copy)
    }

    operator fun get(row: Int, column: Int): Int {
        if (row >= rowSize())
            throw IndexOutOfBoundsException("Row index is out of bounds")
        if (column >= columnSize())
            throw IndexOutOfBoundsException("Column index is out of bounds")
        return matrix[row][column]
    }

    override fun equals(other: Any?): Boolean {
        if (other !is Matrix) return false

        if (rowSize() != other.rowSize() || columnSize() != other.columnSize()) return false

        for (row in matrix.indices)
            for (column in matrix[row].indices)
                if (matrix[row][column] != other[row, column])
                    return false
        return true
    }

    // Duplicate the matrix
    fun copy() : Matrix = Matrix(matrix)

    // Supports: matrixA - matrixB, matrixA -= MatrixB
    operator fun minus(other: Matrix): Matrix {
        if (rowSize() != other.rowSize() || columnSize() != other.columnSize())
            throw IllegalArgumentException("Dimensions are not equal")
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column] - other[row, column]
        return Matrix(copy)
    }

    // Supports: matrix - scalar, matrix -= scalar
    operator fun minus(scalar: Int): Matrix {
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column] - scalar
        return Matrix(copy)
    }

    override fun toString(): String {
        val result = Text("[")
        for (row in 0 until rowSize()) {
            result.append('[')
            for (column in 0 until columnSize()) {
                result.append(this[row, column])
                if (column + 1 < columnSize()) result.append(',')
            }
            result.append(']')

            if (row + 1 < rowSize()) result.append(',')
        }
        return result.append(']').toString()
    }

    // Supports: matrixA * matrixB, matrixA *= MatrixB
    operator fun times(other: Matrix): Matrix {
        if (rowSize() != other.rowSize() || columnSize() != other.columnSize())
            throw IllegalArgumentException("Dimensions are not equal")
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column] * other[row, column]
        return Matrix(copy)
    }

    // Supports: matrix * scalar, matrix *= scalar
    operator fun times(scalar: Int): Matrix {
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column] * scalar
        return Matrix(copy)
    }

    // Supports: matrixA / matrixB, matrixA /= MatrixB
    operator fun div(other: Matrix): Matrix {
        if (rowSize() != other.rowSize() || columnSize() != other.columnSize())
            throw IllegalArgumentException("Dimensions are not equal")
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column] / other[row, column]
        return Matrix(copy)
    }

    // Supports: matrix / scalar, matrix = scalar
    operator fun div(scalar: Int): Matrix {
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column] / scalar
        return Matrix(copy)
    }

    override fun hashCode(): Int {
        return matrix.hashCode()
    }

    operator fun unaryMinus(): Matrix {
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = -matrix[row][column]
        return Matrix(copy)
    }

    operator fun unaryPlus(): Matrix = this.copy()

    fun copyList() : List<IntArray> {
        val copy: List<IntArray> =
            List(rowSize()) { IntArray(columnSize()) { 0 } }
        for (row in matrix.indices)
            for (column in matrix[row].indices)
                copy[row][column] = matrix[row][column]
        return copy
    }
}
