package practicalWork7

import practicalWork3.*
import java.nio.file.Paths

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    val textFile = TextFile()
    val path = Paths.get("").toAbsolutePath().toString() + "\\src\\main\\kotlin\\practicalWork7\\data\\"
    val inputJsonPath  = path + "input.json"
    val outputJsonPath = path + "output.json"

    val shapeSerialization = ShapeSerialization()
    val shapes = shapeSerialization.decode<MutableList<Shape>>(textFile.read(inputJsonPath))

    val factory = ShapeFactoryImpl()
    repeat (5) {
        shapes.add(factory.createRandomShape())
    }

    val encoded = shapeSerialization.encode<List<Shape>>(shapes)
    textFile.write(encoded, outputJsonPath)
}
