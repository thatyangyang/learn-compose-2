package com.yang.example.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun BadgeExample() {
    BadgedBox(
        badge = {
            Badge()
        }
    ) {
        Icon(Icons.Filled.Email, "Email")
    }
}

@Preview
@Composable
fun BadgeInteractiveExample() {
    var itemCount by remember { mutableIntStateOf(0) }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        BadgedBox(
            badge = {
                if (itemCount > 0) {
                    Badge(containerColor = Color.Red, contentColor = Color.White) {
                        Text("$itemCount")
                    }
                }
            }
        ) {
            Icon(Icons.Filled.ShoppingCart, "Shopping cart")
        }

        Button(onClick = { itemCount++ }) {
            Text("Add item")
        }
    }
}