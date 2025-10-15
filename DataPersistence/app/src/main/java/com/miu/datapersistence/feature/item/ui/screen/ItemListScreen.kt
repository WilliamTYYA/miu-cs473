package com.miu.datapersistence.feature.item.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.miu.datapersistence.core.database.InventoryDatabase
import com.miu.datapersistence.feature.item.data.ItemRepositoryImpl
import com.miu.datapersistence.feature.item.ui.state.ItemEditorUiState
import com.miu.datapersistence.feature.item.ui.viewmodel.ItemEditorViewModel
import com.miu.datapersistence.feature.item.ui.viewmodel.ItemListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemListScreen(modifier: Modifier = Modifier) {
    // we need app context
    val context = LocalContext.current
    val applicationContext = context.applicationContext
    //Get the db instance
    val database: InventoryDatabase = remember(applicationContext) {
        InventoryDatabase.getDatabase(applicationContext)
    }
    // get the dao instance
    val itemDao = remember { database.itemDao() }
    val itemListViewModel: ItemListViewModel = viewModel {
        ItemListViewModel(
            ItemRepositoryImpl(itemDao)
        )
    }
    // get the state from itemListviewModel
    val itemListUiState by itemListViewModel.itemListUiState.collectAsStateWithLifecycle()

    val itemEditorViewModel: ItemEditorViewModel = viewModel {
        ItemEditorViewModel(
            ItemRepositoryImpl(itemDao)
        )
    }
    val itemEditorUiState by itemEditorViewModel.itemEditorUiState.collectAsStateWithLifecycle()

    val addDialog = rememberSaveable { mutableStateOf(false) }
    val editDialog = rememberSaveable { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // Open a dialog to add the item
                    addDialog.value = true

                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "add"
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(itemListUiState.items) {
                ListItem(
                    headlineContent = {
                        Text(it.name)
                    },
                    supportingContent = {
                        Text(
//                            "${it.price} x ${it.quantity} = ${it.price * it.quantity}"
                            it.price.toString()
                        )
                    },
                    trailingContent = {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                "edit",
                                modifier = modifier
                                    .clickable {
                                        // take the current item
                                        itemEditorViewModel.setCurrentItemUiState(it)
                                        editDialog.value = true
                                    }
                            )
                            Spacer(modifier=Modifier.padding(10.dp))
                            Icon(
                                imageVector = Icons.Default.Delete,
                                "delete",
                                modifier = modifier
                                    .clickable {
                                        itemListViewModel.deleteItem(it)
                                    }
                            )
                        }
                    }
                )
            }
        }

    }

    if (addDialog.value) {
        AddDialog(
            onDismissRequest = { addDialog.value = false },
            itemEditorUiState = itemEditorUiState,
            itemEditorViewModel = itemEditorViewModel
        )
    }

    if (editDialog.value) {
        BasicAlertDialog(
            onDismissRequest = {
                editDialog.value = false
            }
        ) {
            Surface(
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight(),
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = itemEditorUiState.name,
                        onValueChange = {
                            itemEditorViewModel.updateItemName(it)
                        },
                        label = { Text("Name") }
                    )

                    OutlinedTextField(
                        value = itemEditorUiState.price,
                        onValueChange = {
                            itemEditorViewModel.updateItemPrice(it)
                        },
                        label = { Text("Price") }
                    )

                    OutlinedTextField(
                        value = itemEditorUiState.quantity,
                        onValueChange = {
                            itemEditorViewModel.updateQuantity(it)
                        },
                        label = { Text("Quantity") }
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = {
                            itemEditorViewModel.updateItem()
                            editDialog.value = false
                        },
                        modifier = Modifier.align(Alignment.End),
                    ) {
                        Text("Confirm")
                    }
                }
            }
        }
    }

    LaunchedEffect(
        itemEditorUiState.isSuccess,

    ) {
        if (itemEditorUiState.isSuccess == true) {
            itemEditorViewModel.resetCreateUiState()
        }
    }
}
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddDialog(
    onDismissRequest: () -> Unit,
    itemEditorUiState: ItemEditorUiState,
    itemEditorViewModel: ItemEditorViewModel
) {
    BasicAlertDialog(
        onDismissRequest = {
//            addDialog.value = false
            onDismissRequest()
        }
    ) {
        Surface(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight(),
            shape = MaterialTheme.shapes.large,
            tonalElevation = AlertDialogDefaults.TonalElevation,
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = itemEditorUiState.name,
                    onValueChange = {
                        itemEditorViewModel.updateItemName(it)
                    },
                    label = { Text("Name") }
                )

                OutlinedTextField(
                    value = itemEditorUiState.price,
                    onValueChange = {
                        itemEditorViewModel.updateItemPrice(it)
                    },
                    label = { Text("Price") }
                )

                OutlinedTextField(
                    value = itemEditorUiState.quantity,
                    onValueChange = {
                        itemEditorViewModel.updateQuantity(it)
                    },
                    label = { Text("Quantity") }
                )

                Spacer(modifier = Modifier.height(24.dp))
                TextButton(
                    onClick = {
                        itemEditorViewModel.insertItem()
//                        addDialog.value = false
                        onDismissRequest()
                    },
                    modifier = Modifier.align(Alignment.End),
                ) {
                    Text("Confirm")
                }
            }
        }
    }
}