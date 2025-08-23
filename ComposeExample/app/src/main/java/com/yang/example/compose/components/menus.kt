package com.yang.example.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun MinimalDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.padding(16.dp)) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            DropdownMenuItem(
                text = { Text("Option 1") },
                onClick = {}
            )
            DropdownMenuItem(
                text = { Text("Option 2") },
                onClick = {}
            )
        }
    }
}


@Preview
@Composable
fun LongBasicDropdownMenu() {
    var expanded by remember { mutableStateOf(false) }
    val menuItemData = List(100) {
        "option ${it + 1}"
    }

    Box(modifier = Modifier.padding(16.dp)) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            menuItemData.forEach {
                DropdownMenuItem(
                    text = { Text(it) },
                    onClick = {}
                )
            }
        }
    }
}

@Preview
@Composable
fun DropdownMenuWithDetails() {
    var expanded by remember { mutableStateOf(false) }
    val menuItemData = List(100) {
        "option ${it + 1}"
    }

    Box(modifier = Modifier.padding(16.dp)) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }) {
            menuItemData.forEachIndexed { index, option ->
                if (index % 2 == 0) {
                    HorizontalDivider(thickness = 2.dp)
                }
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {},
                    leadingIcon = {
                        Icon(Icons.Default.Face, contentDescription = "")
                    },
                    trailingIcon = {
                        Icon(Icons.Default.Favorite, contentDescription = "")
                    }
                )
            }
        }
    }
}