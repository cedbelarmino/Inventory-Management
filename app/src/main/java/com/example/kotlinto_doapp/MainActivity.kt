package com.example.kotlinto_doapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.kotlinto_doapp.presentation.inventory.InventoryScreen
import com.example.kotlinto_doapp.presentation.inventory.InventoryViewModel
import com.example.kotlinto_doapp.ui.theme.InventoryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            InventoryAppTheme {
                val viewModel: InventoryViewModel = viewModel(factory = InventoryViewModel.Factory)
                val state by viewModel.uiState.collectAsState()

                InventoryScreen(
                    state = state,
                    onAddItem = viewModel::addItem,
                    onAdjustStock = viewModel::adjustStock,
                    onDeleteItem = viewModel::deleteItem
                )
            }
        }
    }
}
