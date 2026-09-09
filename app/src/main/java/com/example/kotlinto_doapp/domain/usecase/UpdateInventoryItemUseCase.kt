package com.example.kotlinto_doapp.domain.usecase

import com.example.kotlinto_doapp.domain.model.InventoryItem
import com.example.kotlinto_doapp.domain.repository.InventoryRepository

class UpdateInventoryItemUseCase(
    private val repository: InventoryRepository
) {
    suspend operator fun invoke(item: InventoryItem) {
        repository.updateItem(item)
    }
}
