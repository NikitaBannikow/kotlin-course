package practicalWork6

import practicalWork3.Shape

class ShapeCollector<T : Shape> {
    private val allShapes = mutableListOf<T>()

    fun add(shape: T) = allShapes.add(shape)
    fun addAll(shapes: List<T>) = allShapes.addAll(shapes)
    fun getAll(): List<T> = allShapes.toList()
    fun getAllSorted(comparator: Comparator<in T>): List<T> = allShapes.sortedWith(comparator)
    fun getAllByClass(shapeClass: Class<out T>): List<T> = allShapes.filterIsInstance(shapeClass)
}
