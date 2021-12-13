package practical_work4

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val matrixA = MutableMatrix(listOf (
        intArrayOf(-2, 0, 2),
        intArrayOf( 3, 5, 1)
    ))

    println("MatrixA $matrixA")
    val matrixB = MutableMatrix(listOf (
        intArrayOf(4, 1, 8),
        intArrayOf(5, 5, 6)
    ))
    println("MatrixB $matrixA")
    matrixA -= matrixB
    matrixA *= matrixB
    val matrixC = -matrixA
    var matrixD = matrixC - 3
    matrixD /= 2
    val matrixE = Matrix(listOf (
        intArrayOf(10, -1, 22),
        intArrayOf(3, -1, 13)
    ))
    println("Result (shall be 0) ${matrixD - matrixE}")
}
