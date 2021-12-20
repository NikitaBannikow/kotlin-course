package practicalWork1

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class TextFormatterUnitTest {
    @Test
    internal fun line_length_shll_be_positive() {
        var thrown = false

        try {
            alignText("This is a test!", 0)
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun simple_test() {
        assertEquals("This is a\ntest!", alignText("This is a test!", 9))
    }

    @Test
    internal fun tab_test() {
        assertEquals("This is\na test!", alignText("This is\t\na\ttest!", 9))
    }

    @Test
    internal fun tabs_whites_test() {
        assertEquals("This is\na test!", alignText("\tThis    is    \na\ttest!   ", 9))
    }

    @Test
    internal fun return_test() {
        assertEquals("\nThis is\na test!\n", alignText("\nThis is \na test!\n", 9))
    }

    @Test
    internal fun big_word_test1() {
        assertEquals("Very_long_\n" +
                "big_word\n" +
                "test!", alignText("Very_long_big_word test!", 10))
    }

    @Test
    internal fun big_word_test2() {
        assertEquals("Very_long_big_word\ntest!", alignText("Very_long_big_word test!", 18))
    }

    @Test
    internal fun big_word_test3() {
        assertEquals("This is a\nvery_long_\nbig_word\ntest!",
            alignText("This is a very_long_big_word test!", 10))
    }

    @Test
    internal fun align_left_test1() {
         assertEquals("This is a test!",
            alignText("     This is a test!   ", 100))
    }

    @Test
    internal fun align_right_test1() {
        assertEquals("     This is a test!",
            alignText("This is a test!", 20, alignment = Alignment.RIGHT))
    }

    @Test
    internal fun align_right_test2() {
        assertEquals(" This is a\n" +
                "very_long_\n" +
                "  big_word\n" +
                "     test!",
            alignText("This is a very_long_big_word test!", 10, alignment = Alignment.RIGHT))
    }

    @Test
    internal fun align_center_test1() {
        assertEquals("  This is a test!",
            alignText("This is a test!", 20, alignment = Alignment.CENTER))
    }

    @Test
    internal fun align_justify_test1() {
        assertEquals("This  is   a   test!",
            alignText("This is a test!", 20, alignment = Alignment.JUSTIFY))
    }

    @Test
    internal fun align_justify_test2() {
        assertEquals("Kotlin  is  a  cross-platform,   statically   typed,   general-purpose   " +
                "programming   language   with  type  inference.",
            alignText("Kotlin is a cross-platform, statically typed, general-purpose " +
                    "programming language with type inference.", 120, alignment = Alignment.JUSTIFY))
    }
}
