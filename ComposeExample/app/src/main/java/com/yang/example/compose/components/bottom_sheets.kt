package com.yang.example.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetSimpleDemo() {
    ModalBottomSheet(onDismissRequest = {}) {
        ListItem(headlineContent = {
            Text("123")
        })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomSheetDemo() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show Bottom Sheet") },
                icon = { Icon(Icons.Filled.Add, "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { contentPadding ->

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                modifier = Modifier.padding(contentPadding)
            ) {
                Button(onClick = {
                    scope.launch {
                        sheetState.hide()
                    }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }) {
                    Text("Hide bottom sheet")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun PartialBottomSheet() {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { showBottomSheet = true }) {
            Text("Display partial bootom sheet")
        }

        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    "Swipe up to open sheet. Swipe down to close sheet.",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}
