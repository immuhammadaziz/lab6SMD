package task03

class Delivery(val name: String) {
    companion object {
        val PICKUP = Delivery("PICKUP")
        val COURIER = Delivery("COURIER")
        val EXPRESS = Delivery("EXPRESS")
        val DRONE = Delivery("DRONE")

        val entries = listOf(PICKUP, COURIER, EXPRESS, DRONE)
    }
}

fun shippingCost(type: Delivery, weightKg: Double): Double = when (type.name) {
    "PICKUP" -> 0.0
    "COURIER" -> 15_000 + 2_000 * weightKg
    "EXPRESS" -> 40_000 + 5_000 * weightKg
    "DRONE" -> 25_000 + 3_000 * weightKg
    else -> 0.0
}

fun main() {
    println("Delivery types: ${Delivery.entries.joinToString { it.name }}")
    print("Delivery type: ")
    val typeName = readln().trim()
    val type = Delivery.entries.firstOrNull { it.name.equals(typeName, ignoreCase = true) }
    if (type == null) {
        println("Unknown delivery type")
        return
    }
    print("Weight in kg: ")
    val weight = readln().trim().toDoubleOrNull()
    if (weight == null || weight < 0) {
        println("Invalid weight")
        return
    }
    println("Shipping cost: ${"%.0f".format(shippingCost(type, weight))} UZS")
}
