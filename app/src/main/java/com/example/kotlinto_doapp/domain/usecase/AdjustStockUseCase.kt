package com.example.kotlinto_doapp.domain.usecase

import com.example.kotlinto_doapp.domain.model.InventoryItem
import com.example.kotlinto_doapp.domain.repository.InventoryRepository

class AdjustStockUseCase(
    private val repository: InventoryRepository
) {
    suspend operator fun invoke(item: InventoryItem, delta: Int) {
        val updated = item.copy(quantity = (item.quantity + delta).coerceAtLeast(0))
        repository.updateItem(updated)
    }
}
