package practicalWork2

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    // Test from description of Practical Work
    calculatorTest("-3+4^+4", "253.0")
    calculatorTest("(+1 + (-1 + +2)) * 9", "18.0")
    calculatorTest("(4+3)*9/ -4", "-15.75")
    calculatorTest("((-4+3))*8/4", "-2.0")

    // With constant
    calculatorTest("3 * pi + 1", "10.42")
    calculatorTest("3 * e + 1 + e/2", "10.51")

    // See also Unit tests
}

fun calculatorTest(expression: String, expected: String) {
    val rpn = normalizeToRPN(expression)
    val result = rpnCalculate(rpn)
    println("Exp: $expression = $result (Expected: $expected)")
    println("   RPN: $rpn")
    println()
}