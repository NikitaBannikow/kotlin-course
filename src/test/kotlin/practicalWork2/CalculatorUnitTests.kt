package practicalWork2

import org.junit.Test
import java.lang.IllegalArgumentException
import kotlin.test.assertEquals

class CalculatorUnitTests {
    @Test
    internal fun add_test() {
        val result = calculate("2+1")
        assertEquals(3.0, result)
    }

    @Test
    internal fun sub_test() {
        val result = calculate("9-2")
        assertEquals(7.0, result)
    }

    @Test
    internal fun div_test() {
        val result = calculate("9/3")
        assertEquals(3.0, result)
    }

    @Test
    internal fun multiplication_test() {
        val result = calculate("9*3")
        assertEquals(27.0, result)
    }

    @Test
    internal fun test_with_brackets1() {
        val result = calculate("((4+3)*5)/9")
        assertEquals(3.8, result, 0.1)
    }

    @Test
    internal fun test_exp_with_pow() {
        val result = calculate("2 ^ 3")
        assertEquals(8.0, result, 0.1)
    }

    @Test
    internal fun test_exp_priority_1() {
        val result = calculate("(2 ^ 3 + 1)")
        assertEquals(9.0, result, 0.1)
    }
    @Test
    internal fun test_exp_priority_2() {
        val result = calculate("(1 + 2 ^ 3)")
        assertEquals(9.0, result, 0.1)
    }

    @Test
    internal fun test_exp_unary_1() {
        val result = calculate("(+1 + (-1 + +2)) * 9")
        assertEquals(18.0, result, 0.1)
    }

    @Test
    internal fun test_exp_priority_3() {
        val result = calculate("(1 + 9 * 2 ^ 3)")
        assertEquals(73.0, result, 0.1)
    }

    @Test
    internal fun test_exp_priority_4() {
        val result = calculate("(9 - 9 / 3 ^ 2)")
        assertEquals(8.0, result, 0.1)
    }

    @Test
    internal fun test_exp_pi() {
        val result = calculate("pi")
        assertEquals(3.1415, result, 0.4)
    }

    @Test
    internal fun test_exp_priority_and_pi1() {
        val result = calculate("1 + 3 * pi")
        assertEquals(10.42, result, 0.1)
    }

    @Test
    internal fun test_exp_priority_and_pi2() {
        val result = calculate("3 * pi + 1")
        assertEquals(10.42, result, 0.1)
    }

    @Test
    internal fun test_exp_priority_and_e1() {
        val result = calculate("3 * e + 1 + e/2")
        assertEquals(10.51, result, 0.1)
    }

    @Test
    internal fun test_exp_priority_and_e2() {
        val result = calculate("3 * e")
        assertEquals(8.15, result, 0.1)
    }

    @Test
    internal fun err_test_exp1() {
        var thrown = false

        try {
            calculate("pi)")
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_test_exp2() {
        var thrown = false

        try {
            calculate("(()))")
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_test_exp3() {
        var thrown = false

        try {
            calculate("+4 */ 33")
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_test_exp4() {
        var thrown = false

        try {
            calculate("*3 + 3")
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }

    @Test
    internal fun err_test_exp5() {
        var thrown = false

        try {
            calculate("3 * P + 3")
        } catch (e: IllegalArgumentException) {
            thrown = true
        }
        assert(thrown)
    }
}