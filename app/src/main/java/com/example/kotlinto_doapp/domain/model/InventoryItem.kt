package com.example.kotlinto_doapp.domain.model

data class InventoryItem(
    val id: Int = 0,
    val name: String,
    val sku: String = "",
    val category: String = "General",
    val quantity: Int = 0,
    val reorderLevel: Int = 5
) {
    val isLowStock: Boolean
        get() = quantity <= reorderLevel
}
