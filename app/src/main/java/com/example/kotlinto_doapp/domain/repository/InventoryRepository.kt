package com.example.kotlinto_doapp.domain.repository

import com.example.kotlinto_doapp.domain.model.InventoryItem
import kotlinx.coroutines.flow.Flow

interface InventoryRepository {

    fun getItems(): Flow<List<InventoryItem>>

    suspend fun addItem(item: InventoryItem)

    suspend fun updateItem(item: InventoryItem)

    suspend fun deleteItem(item: InventoryItem)
}
