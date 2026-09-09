package com.example.kotlinto_doapp.presentation.inventory

import com.example.kotlinto_doapp.domain.model.InventoryItem

data class InventoryUiState(
    val items: List<InventoryItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val totalSkus: Int
        get() = items.size

    val totalUnits: Int
        get() = items.sumOf { it.quantity }

    val lowStockCount: Int
        get() = items.count { it.isLowStock }
}
