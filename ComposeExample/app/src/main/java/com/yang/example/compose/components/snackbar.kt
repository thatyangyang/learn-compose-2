package com.yang.example.compose.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch


@Composable
@Preview
fun SnackBarExample() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Filled.Info, "") },
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("This is a snackbar message")
                    }
                }
            )
        }
    ) {
        Text(modifier = Modifier.padding(it), text = "Content")
    }
}

@Composable
@Preview
fun SnackBarActionExample() {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Filled.Info, "") },
                onClick = {
                    scope.launch {
                        val reuslt = snackbarHostState.showSnackbar(
                            message = "Snackbar",
                            actionLabel = "Action",
                            duration = SnackbarDuration.Indefinite
                        )

                        when (reuslt) {
                            SnackbarResult.Dismissed -> {}
                            SnackbarResult.ActionPerformed -> {
                                println("Performed")
                            }
                        }
                    }
                }
            )
        }
    ) {
        Text(modifier = Modifier.padding(it), text = "Content")
    }
}

