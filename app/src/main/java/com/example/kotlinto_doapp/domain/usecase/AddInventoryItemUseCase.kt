package com.example.kotlinto_doapp.domain.usecase

import com.example.kotlinto_doapp.domain.model.InventoryItem
import com.example.kotlinto_doapp.domain.repository.InventoryRepository

class AddInventoryItemUseCase(
    private val repository: InventoryRepository
) {
    suspend operator fun invoke(
        name: String,
        sku: String,
        category: String,
        quantity: Int,
        reorderLevel: Int
    ) {
        if (name.isBlank()) {
            return
        }

        repository.addItem(
            InventoryItem(
                name = name.trim(),
                sku = sku.trim(),
                category = category.trim().ifBlank { "General" },
                quantity = quantity.coerceAtLeast(0),
                reorderLevel = reorderLevel.coerceAtLeast(0)
            )
        )
    }
}
