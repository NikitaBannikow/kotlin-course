package practicalWork7

import kotlinx.serialization.*
import kotlinx.serialization.modules.*
import kotlinx.serialization.json.Json

import practicalWork3.Shape
import practicalWork3.Circle
import practicalWork3.Rectangle
import practicalWork3.Square
import practicalWork3.Triangle

class ShapeSerialization {
    val json = Json {
        prettyPrint = true

        serializersModule = SerializersModule {
            polymorphic(Shape::class) {
                subclass(Circle::class)
                subclass(Rectangle::class)
                subclass(Square::class)
                subclass(Triangle::class)
            }
        }
    }

    inline fun <reified T> encode(shapes: T): String = json.encodeToString(shapes)
    inline fun <reified T> decode(text: String): T = json.decodeFromString(text)
}
