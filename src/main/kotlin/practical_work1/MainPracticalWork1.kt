package practical_work1

const val inputText =
    "Kotlin is a cross-platform, statically typed, general-purpose programming language with type inference.\n" +
            "Kotlin is designed to interoperate fully with Java, and the JVM version of Kotlin's standard " +
            "library depends on the Java Class Library, but type inference allows its syntax to be more concise. \n" +
            "Kotlin mainly targets the JVM, but also compiles to JavaScript (e.g., for frontend web applications " +
            "using React) or native code (via LLVM); e.g., for native iOS apps sharing business logic with Android " +
            "apps. Language development costs are borne by JetBrains, while the Kotlin Foundation protects the " +
            "Kotlin trademark."

@Suppress("UNUSED_PARAMETER")
fun main(args: Array<String>) {
    println("Original text:")
    println("==============")
    println(inputText)
    println()

    makeFormatAndPrint(30, Alignment.LEFT)
    makeFormatAndPrint(40, Alignment.RIGHT)
    makeFormatAndPrint(35, Alignment.CENTER)
    makeFormatAndPrint(60, Alignment.JUSTIFY)
}

fun makeFormatAndPrint(width:Int, alignment: Alignment) {
    println("Formatted text (width:$width, alignment: $alignment)")
    println("===========================================================")
    println(alignText(inputText, width, alignment))
    println()
}