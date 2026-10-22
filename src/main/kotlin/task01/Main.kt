package task01

class OrderStatus(val name: String, val ordinal: Int) {
    companion object {
        val NEW = OrderStatus("NEW", 0)
        val PAID = OrderStatus("PAID", 1)
        val SHIPPED = OrderStatus("SHIPPED", 2)
        val DELIVERED = OrderStatus("DELIVERED", 3)
        val CANCELLED = OrderStatus("CANCELLED", 4)

        val entries = listOf(NEW, PAID, SHIPPED, DELIVERED, CANCELLED)

        fun valueOf(text: String): OrderStatus {
            return entries.first { it.name.equals(text, ignoreCase = true) }
        }
    }
}

fun main() {
    println("Statuses: ${OrderStatus.entries.joinToString { it.name }}")
    print("Current status of your order: ")
    val text = readln().trim().uppercase()
    val status = try {
        OrderStatus.valueOf(text)
    } catch (e: NoSuchElementException) {
        println("'$text' is not a status. Valid: ${OrderStatus.entries.map { it.name }}")
        return
    }
    println("You entered ${status.name}: step ${status.ordinal + 1} of ${OrderStatus.entries.size}")
    println(OrderStatus.PAID === OrderStatus.valueOf("PAID"))
    val next = OrderStatus.entries.getOrNull(status.ordinal + 1)
    println(if (next == null) "This is the last step." else "Next step: ${next.name}")
}
