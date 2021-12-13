package practicalWork1

import org.junit.Test

class TextFormatterUnitTest {
    @Test
    internal fun simple_test() {
        test("simple_test", "This is a test!", "This is a\ntest!", 9)
    }

    @Test
    internal fun tab_test() {
        test("tab_test", "This is\t\na\ttest!", "This is\na test!", 9)
    }

    @Test
    internal fun tabs_whites_test() {
        test("simple_test_3", "\tThis    is    \na\ttest!   ", "This is\na test!", 9)
    }

    @Test
    internal fun return_test() {
        test("return_test", "\nThis is \na test!\n", "\nThis is\na test!\n", 9)
    }

    @Test
    internal fun big_word_test1() {
        test("big_word_test1", "Very_long_big_word test!",
            "Very_long_\n" +
                    "big_word\n" +
                    "test!", 10)
    }

    @Test
    internal fun big_word_test2() {
        test("big_word_test2", "Very_long_big_word test!",
            "Very_long_big_word\n" +
                    "test!", 18)
    }

    @Test
    internal fun big_word_test3() {
        test("big_word_test3", "This is a very_long_big_word test!",
            "This is a\n" +
                      "very_long_\n" +
                      "big_word\n" +
                      "test!", 10)
    }

    @Test
    internal fun align_left_test1() {
        test("align_left_test1", "     This is a test!   ",
            "This is a test!", 100)
    }

    @Test
    internal fun align_right_test1() {
        test("align_right_test1", "This is a test!",
            "     This is a test!", 20, alignment = Alignment.RIGHT)
    }

    @Test
    internal fun align_right_test2() {
        test("align_right_test2", "This is a very_long_big_word test!",
            " This is a\n" +
                     "very_long_\n" +
                     "  big_word\n" +
                     "     test!", 10, alignment = Alignment.RIGHT)
    }

    @Test
    internal fun align_center_test1() {
        test("align_center_test1", "This is a test!",
            "  This is a test!", 20, alignment = Alignment.CENTER)
    }

    @Test
    internal fun align_justify_test1() {
        test("align_justify_test1",
            "This is a test!",
            "This  is   a   test!",
            20, alignment = Alignment.JUSTIFY)
    }

    @Test
    internal fun align_justify_test2() {
        test("align_justify_test2",
            "Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference.",
            "Kotlin  is  a  cross-platform,   statically   typed,   general-purpose   " +
                    "programming   language   with  type  inference.",
            120, alignment = Alignment.JUSTIFY)
    }

    private fun test(testName:String,
                     inputText: String,
                     outputText:String,
                     lineWidth: Int = 120,
                     alignment: Alignment = Alignment.LEFT) {
        val result = alignText(inputText, lineWidth, alignment)
        if (result != outputText) {
            throw Exception("Test $testName failed:\nInput:\n$inputText\n\nExpected:\n$outputText\n\nActual:\n$result")
        }
    }
}