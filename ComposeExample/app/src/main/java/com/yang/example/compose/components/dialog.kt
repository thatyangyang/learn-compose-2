package com.yang.example.compose.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun DialogExample() {
    val openAlertDialog = remember { mutableStateOf(true) }

    when {
        openAlertDialog.value -> {
            AlertDialogExample(
                onDismissRequest = {
                    openAlertDialog.value = false
                },
                onConfirmRequest = {
                    openAlertDialog.value = false
                    println("Confirmed!")
                },
                title = "Title",
                text = "This is an example of AlertDialog",
                icon = Icons.Default.Info
            )
        }
    }
}

@Composable
fun AlertDialogExample(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    title: String,
    text: String,
    icon: ImageVector
) {
    AlertDialog(
        icon = {
            Icon(icon, "Example Icon")
        },
        title = {
            Text(title)
        },
        text = {
            Text(text)
        },
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(
                onClick = onConfirmRequest
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text("Dismiss")
            }
        },
    )
}

// Advanced dialog

