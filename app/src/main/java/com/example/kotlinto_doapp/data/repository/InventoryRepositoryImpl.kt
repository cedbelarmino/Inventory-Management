package com.example.kotlinto_doapp.data.repository

import com.example.kotlinto_doapp.domain.model.InventoryItem
import com.example.kotlinto_doapp.domain.repository.InventoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InventoryRepositoryImpl : InventoryRepository {

    private val items = MutableStateFlow(
        listOf(
            InventoryItem(
                id = 1,
                name = "A4 Copy Paper",
                sku = "PAP-A4",
                category = "Office",
                quantity = 12,
                reorderLevel = 10
            ),
            InventoryItem(
                id = 2,
                name = "USB-C Cable",
                sku = "CAB-USBC",
                category = "Electronics",
                quantity = 4,
                reorderLevel = 8
            ),
            InventoryItem(
                id = 3,
                name = "Safety Gloves",
                sku = "SAF-GLV",
                category = "PPE",
                quantity = 25,
                reorderLevel = 15
            ),
            InventoryItem(
                id = 4,
                name = "Packing Tape",
                sku = "PKG-TAP",
                category = "Warehouse",
                quantity = 3,
                reorderLevel = 6
            )
        )
    )

    private var nextId = 5

    override fun getItems(): Flow<List<InventoryItem>> = items.asStateFlow()

    override suspend fun addItem(item: InventoryItem) {
        val id = if (item.id == 0) nextId++ else item.id
        items.update { current -> current + item.copy(id = id) }
    }

    override suspend fun updateItem(item: InventoryItem) {
        items.update { current ->
            current.map { existing -> if (existing.id == item.id) item else existing }
        }
    }

    override suspend fun deleteItem(item: InventoryItem) {
        items.update { current -> current.filterNot { it.id == item.id } }
    }
}
