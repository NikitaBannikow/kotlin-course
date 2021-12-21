package practicalWork4

/**
 * Implemented:
 * - set element at position (row,column)
 * - method toString()
 * - Operations:
 *  - matrixA += matrixB
 *  - matrixA += scalar
 *  - matrixA -= matrixB
 *  - matrixA -= scalar
 *  - matrixA *= matrixB
 *  - matrixA *= scalar
 *  - matrixA /= scalar
 * - Unit Tests
 */
class MutableMatrix(inputMatrix: Array<IntArray>) : Matrix(inputMatrix) {
    operator fun set(row: Int, column: Int, value: Int) {
        if (row >= rowSize || row < 0)
            throw IndexOutOfBoundsException("Row index is out of bounds")
        if (column >= columnSize || column < 0)
            throw IndexOutOfBoundsException("Column index is out of bounds")
        matrix[row][column] = value
    }

    operator fun plusAssign(other: Matrix) {
        if (rowSize != other.rowSize || columnSize != other.columnSize)
            throw IllegalArgumentException("Dimensions are not equal")
        for (row in 0 until rowSize) {
            for (column in 0 until columnSize) {
                matrix[row][column] += other[row, column]
            }
        }
    }

    operator fun plusAssign(scalar: Int) {
        for (row in 0 until rowSize) {
            for (column in 0 until columnSize) {
                matrix[row][column] += scalar
            }
        }
    }

    operator fun minusAssign(other: Matrix) {
        if (rowSize != other.rowSize || columnSize != other.columnSize)
            throw IllegalArgumentException("Dimensions are not equal")
        for (row in 0 until rowSize) {
            for (column in 0 until columnSize) {
                matrix[row][column] -= other[row, column]
            }
        }
    }

    operator fun minusAssign(scalar: Int) {
        for (row in 0 until rowSize) {
            for (column in 0 until columnSize) {
                matrix[row][column] -= scalar
            }
        }
    }

    operator fun timesAssign(other: Matrix) {
        if (rowSize != other.rowSize || columnSize != other.columnSize)
            throw IllegalArgumentException("Dimensions are not equal")
        for (row in 0 until rowSize) {
            for (column in 0 until columnSize) {
                matrix[row][column] *= other[row, column]
            }
        }
    }

    operator fun timesAssign(scalar: Int) {
        for (row in 0 until rowSize) {
            for (column in 0 until columnSize) {
                matrix[row][column] *= scalar
            }
        }
    }

    operator fun divAssign(scalar: Int) {
        for (row in 0 until rowSize) {
            for (column in 0 until columnSize) {
                matrix[row][column] /= scalar
            }
        }
    }
}
