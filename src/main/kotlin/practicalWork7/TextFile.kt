package practicalWork7

import java.io.File
import java.io.IOException

class TextFile {
    @Throws(IOException::class)
    fun write(text: String, path: String) {
        File(path).writeText(text)
    }

    @Throws(IOException::class)
    fun read(path: String): String = File(path).readText()
}
