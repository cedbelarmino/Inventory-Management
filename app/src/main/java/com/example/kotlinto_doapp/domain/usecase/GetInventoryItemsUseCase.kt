package com.example.kotlinto_doapp.domain.usecase

import com.example.kotlinto_doapp.domain.model.InventoryItem
import com.example.kotlinto_doapp.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow

class GetInventoryItemsUseCase(
    private val repository: InventoryRepository
) {
    operator fun invoke(): Flow<List<InventoryItem>> {
        return repository.getItems()
    }
}
