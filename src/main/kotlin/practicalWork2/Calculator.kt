package practicalWork2

import java.util.*
import kotlin.math.pow
import kotlin.text.StringBuilder as Text

/**
 * Calculator
 * Supported:
 *  - Operations: '+', '-', '*', '/', '^'
 *  - Constants: pi, e
 *  - Operands: digits 0..9
 *  - Unary operations
 *
 * Implementation is based on Reverse Polish notation calculator
 * Link: https://en.wikipedia.org/wiki/Reverse_Polish_notation
 */
fun calculate(exp: String): Double {
    // Turn to RPN notation
    val rpn = normalizeToRPN(exp)
    // And calculate
    return rpnCalculate(rpn)
}

// Supported operations with priorities
val OPS_WITH_PRIORITIES = mapOf(
    '+' to 1,
    '-' to 1,
    '*' to 3,
    '/' to 3,
    '^' to 6
)

// Supported operations
val OPS = OPS_WITH_PRIORITIES.keys

// Unary operations
val UNARY_OPS = setOf('+', '-')

private fun makeOperation(op1: Double, op2: Double, operation: Char): Double {
    return when (operation) {
        '+' -> {
            op1 + op2
        }
        '-' -> {
            op2 - op1
        }
        '*' -> {
            op2 * op1
        }
        '/' -> {
            op2 / op1
        }
        '^' -> {
            op2.pow(op1)
        }
        else -> throw IllegalArgumentException("Unsupported operation $operation")
    }
}

private fun onOperation(exStack: Stack<Any>, operation: Char) {
    try {
        val op1 = exStack.pop()
        val op2 = exStack.pop()

        if (op1 is Double && op2 is Double) {
            val result = makeOperation(op1, op2, operation)
            exStack.push(result)
        } else {
            throw IllegalArgumentException(
                "Invalid operands, at least one is not double op1:$op1, op2:$op2"
            )
        }
    } catch (e: EmptyStackException) {
        throw IllegalArgumentException("Invalid expression")
    }
}

private fun sanitize(expr: String): String {
    // Exclude whitespaces and tabs
    val s = expr.replace("\t", "").replace(" ", "")

    // Exclude internally used chars
    if (s.contains('P')) throw IllegalArgumentException("Unsupported symbol P")
    if (s.contains('$')) throw IllegalArgumentException("Unsupported symbol $")

    // Replace pi to one char
    return s.replace("pi", "P")
}

val LEFT_OPS_OF_UNARY = OPS + '$' + '('

val RIGHT_OPS_OF_OPENING_BRACKET = setOf('(', '+', '-', 'e', 'P')
val RIGHT_OPS_OF_CLOSING_BRACKET = OPS + '$' + ')'

val LEFT_OPS_OF_OPENING_BRACKET = OPS + '$' + '('
val LEFT_OPS_OF_CLOSING_BRACKET = setOf(')', 'P', 'e')

fun normalizeToRPN(inputExpression: String): List<String> {
    // Sanitize expression
    val expression = sanitize(inputExpression)

    // Operations/Operands stack
    val opStack = Stack<Char>()

    // Normalized expression for RPN
    val rpn = mutableListOf<String>()

    var skipNext = false
    var openBrackets = 0
    val charAt = { pos: Int -> if (pos >= 0 && pos < expression.length) expression[pos] else '$' }
    val pushChar = { op: Char -> rpn.add(op.toString()) }
    val pushString = { op: String -> if (op.isNotEmpty()) rpn.add(op) }

    for ((pos, op) in expression.withIndex()) {
        val prevOp = charAt(pos - 1)
        val nextOp = charAt(pos + 1)
        when {
            skipNext -> {
                skipNext = false
                continue
            }
            op in OPS -> {
                // Unary operation detection
                if (op in UNARY_OPS && prevOp in LEFT_OPS_OF_UNARY && nextOp.isDigit()) {
                    pushString(op + nextOp.toString())
                    skipNext = true
                    continue
                }

                // Prioritize operations in stack (temporary save and push back after current)
                val pushBackOps = Text()
                while (!opStack.empty() && opStack.peek() in OPS
                    && OPS_WITH_PRIORITIES[opStack.peek()]!! >= OPS_WITH_PRIORITIES[op]!!
                ) {
                    pushBackOps.append(opStack.pop())
                }
                opStack.push(op)
                pushBackOps.forEach { char -> pushChar(char) }
            }
            op.isDigit() -> pushChar(op)
            op == 'e' -> pushChar(op)
            op == 'P' -> pushChar(op)
            op == '(' -> {
                openBrackets++
                if ((nextOp.isDigit() || nextOp in RIGHT_OPS_OF_OPENING_BRACKET) &&
                    (prevOp in LEFT_OPS_OF_OPENING_BRACKET) &&
                    (expression.length > pos + 1)
                ) {
                    opStack.push('(')
                } else throw IllegalArgumentException("Invalid expression at $pos (opening bracket)")
            }
            op == ')' -> {
                if (--openBrackets >= 0) {

                    if ((prevOp.isDigit() || prevOp in LEFT_OPS_OF_CLOSING_BRACKET) &&
                        nextOp in RIGHT_OPS_OF_CLOSING_BRACKET
                    ) {
                        // Till opening bracket, store operands and push back to stack
                        val pushBackOps = Text()
                        while (!opStack.empty() && opStack.peek() != '(') {
                            pushBackOps.append(opStack.pop())
                        }
                        if (!opStack.empty()) opStack.pop()
                        pushBackOps.forEach { char -> pushChar(char) }
                    } else throw IllegalArgumentException("Invalid expression at $pos (closing bracket)")
                } else throw IllegalArgumentException("Missed opening bracket")
            }
            else -> throw IllegalArgumentException("Unsupported symbol $op in expression at $pos position")
        }
    }
    if (openBrackets > 0) throw IllegalArgumentException("Missing closing bracket")

    while (!opStack.empty()) pushString(opStack.pop().toString())

    return rpn
}

fun rpnCalculate(expression: List<String>): Double {
    val exStack = Stack<Any>()

    fun String.isDigit(): Boolean {
        return when (toIntOrNull()) {
            null -> false
            else -> true
        }
    }

    for ((pos, value) in expression.withIndex()) {
        var char: Char = value[0]
        if (value.length > 1) {
            char = '$'
        }
        when {
            // Value (string) shall be processed first
            value.isDigit() -> exStack.push(value.toDouble())

            // One symbol operation/operand
            char in OPS -> onOperation(exStack, char)
            char == 'P' -> exStack.push(Math.PI)
            char == 'e' -> exStack.push(Math.E)

            // Issues in expression
            char == '$' -> throw IllegalArgumentException("Unsupported operand $value at $pos position")
            else -> throw IllegalArgumentException("Unsupported symbol $char at $pos position")
        }
    }

    val result = exStack.pop()

    if (result !is Double) throw IllegalArgumentException("Invalid type in stack $result")
    if (exStack.isNotEmpty()) throw IllegalArgumentException("Invalid number of brackets or operations positions")

    return result
}
