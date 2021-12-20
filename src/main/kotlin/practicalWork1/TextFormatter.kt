package practicalWork1

import kotlin.text.StringBuilder as Text

enum class Alignment {
    LEFT,
    RIGHT,
    CENTER,
    JUSTIFY
}

fun alignText(
    originalText: String,
    lineWidth: Int = 120,
    alignment: Alignment = Alignment.LEFT
): String {
    if (lineWidth < 1)
        throw IllegalArgumentException("Line width shall be equal or greater 1")

    if (originalText.isEmpty()) return ""

    val notEmpty = { text: Text -> text.isNotEmpty() }

    // Align a line
    val align = fun(line: Text): Text {
        val trimmed = Text(line.replace("\\s+".toRegex(), " ").trim())
        return when (alignment) {
            Alignment.RIGHT -> alignRight(trimmed, lineWidth)
            Alignment.CENTER -> alignCenter(trimmed, lineWidth)
            Alignment.JUSTIFY -> alignJustify(trimmed, lineWidth)
            else -> trimmed
        }
    }

    // Add a word to a line and to a text if required
    fun addWord(word: Text, line: Text, text: Text, toAppend: CharSequence = "") {
        val len = line.length + word.length
        if (len <= lineWidth) {
            line.append(word).append(toAppend)
            word.clear()
        } else {
            if (notEmpty(line)) text.append(align(line)).append('\n')
            line.clear()
            if (word.length <= lineWidth) {
                line.append(word).append(toAppend)
                word.clear()
            } else {
                text.append(word.subSequence(0, lineWidth)).append('\n')
                val rest = word.subSequence(lineWidth, word.length)
                word.clear().append(rest)
                addWord(word, line, text, toAppend)
            }
        }
    }

    val text = Text()
    val line = Text()
    val word = Text()

    for (char in originalText) {
        when (char) {
            '\n' -> {
                if (notEmpty(word)) {
                    addWord(word, line, text)
                }
                if (notEmpty(line)) {
                    text.append(align(line))
                    line.clear()
                }
                text.append('\n')
            }
            ' ', '\t' -> {
                if (notEmpty(word)) {
                    addWord(word, line, text, " ")
                }
            }
            else -> word.append(char)
        }
    }

    if (notEmpty(word)) addWord(word, line, text)
    if (notEmpty(line)) text.append(align(line))

    return text.toString()
}

private fun alignRight(line: Text, lineWidth: Int): Text {
    return Text(line.padStart(lineWidth, ' '))
}

private fun alignCenter(line: Text, lineWidth: Int): Text {
    return Text(line.padStart(line.length + (lineWidth - line.length) / 2 , ' '))
}

private fun alignJustify(line: Text, lineWidth: Int): Text {
    // Check if we can justify
    if (line.count { it == ' ' } == 0) return line

    // Check how many spaces can be added
    var spacesToAdd = lineWidth - line.length
    if (spacesToAdd == 0) return line

    // Split words
    val words = line.split(" ")

    // Spread whitespaces between words from beginning and end
    val spacesSpread = Array(words.size - 1) { 1 }

    var forwardIndex = 0
    var backwardIndex = spacesSpread.size - 1
    var forward = true
    while (spacesToAdd > 0) {
        if (forward) {
            spacesSpread[forwardIndex++]++
            if (forwardIndex == spacesSpread.size) forwardIndex = 0
        } else {
            spacesSpread[backwardIndex--]++
            if (backwardIndex < 0) backwardIndex = spacesSpread.size - 1
        }
        forward = !forward
        spacesToAdd--
    }

    val result = Text()
    for ((index, word) in words.withIndex()) {
        result.append(word)
        if (index < spacesSpread.size) {
            result.append(" ".repeat(spacesSpread[index]))
        }
    }

    return result
}