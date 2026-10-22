package task02

abstract class Operation(val symbol: String) {
    abstract fun apply(a: Double, b: Double): Double

    class Add : Operation("+") {
        override fun apply(a: Double, b: Double) = a + b
    }
    class Subtract : Operation("-") {
        override fun apply(a: Double, b: Double) = a - b
    }
    class Multiply : Operation("*") {
        override fun apply(a: Double, b: Double) = a * b
    }
    class Divide : Operation("/") {
        override fun apply(a: Double, b: Double) = a / b
    }
    class Modulo : Operation("%") {
        override fun apply(a: Double, b: Double) = a % b
    }
    class Power : Operation("^") {
        override fun apply(a: Double, b: Double) = kotlin.math.pow(a, b)
    }

    companion object {
        val entries = listOf(Add(), Subtract(), Multiply(), Divide(), Modulo(), Power())

        fun fromSymbol(symbol: String): Operation? =
            entries.firstOrNull { it.symbol == symbol }
    }
}

fun main() {
    print("Expression (for example 12 * 4): ")
    val parts = readln().trim().split(" ")
    if (parts.size != 3) {
        println("Use: number operator number")
        return
    }
    val a = parts[0].toDoubleOrNull()
    val b = parts[2].toDoubleOrNull()
    val operation = Operation.fromSymbol(parts[1])
    if (a == null || b == null || operation == null) {
        println("Cannot understand '${parts.joinToString(" ")}'")
        return
    }
    if (operation is Operation.Divide && b == 0.0) {
        println("Division by zero")
        return
    }
    println("$a ${operation.symbol} $b = ${operation.apply(a, b)}")
}
