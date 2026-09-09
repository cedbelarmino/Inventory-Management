package com.example.kotlinto_doapp.presentation.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.kotlinto_doapp.data.repository.InventoryRepositoryImpl
import com.example.kotlinto_doapp.domain.model.InventoryItem
import com.example.kotlinto_doapp.domain.usecase.AddInventoryItemUseCase
import com.example.kotlinto_doapp.domain.usecase.AdjustStockUseCase
import com.example.kotlinto_doapp.domain.usecase.DeleteInventoryItemUseCase
import com.example.kotlinto_doapp.domain.usecase.GetInventoryItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InventoryViewModel(
    private val getInventoryItems: GetInventoryItemsUseCase,
    private val addInventoryItem: AddInventoryItemUseCase,
    private val adjustStockUseCase: AdjustStockUseCase,
    private val deleteInventoryItem: DeleteInventoryItemUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(InventoryUiState(isLoading = true))
    val uiState: StateFlow<InventoryUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getInventoryItems().collect { items ->
                _uiState.update {
                    it.copy(items = items, isLoading = false, error = null)
                }
            }
        }
    }

    fun addItem(
        name: String,
        sku: String,
        category: String,
        quantity: Int,
        reorderLevel: Int
    ) {
        viewModelScope.launch {
            addInventoryItem(name, sku, category, quantity, reorderLevel)
        }
    }

    fun adjustStock(item: InventoryItem, delta: Int) {
        viewModelScope.launch {
            adjustStockUseCase(item, delta)
        }
    }

    fun deleteItem(item: InventoryItem) {
        viewModelScope.launch {
            deleteInventoryItem(item)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = InventoryRepositoryImpl()
                return InventoryViewModel(
                    getInventoryItems = GetInventoryItemsUseCase(repository),
                    addInventoryItem = AddInventoryItemUseCase(repository),
                    adjustStockUseCase = AdjustStockUseCase(repository),
                    deleteInventoryItem = DeleteInventoryItemUseCase(repository)
                ) as T
            }
        }
    }
}
