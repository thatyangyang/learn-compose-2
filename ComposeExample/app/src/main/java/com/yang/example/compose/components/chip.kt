package com.yang.example.compose.components

import android.util.Log
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
fun AssistChipExample() {
    AssistChip(
        onClick = { Log.d("AssistChipExample", "AssistChip clicked") },
        label = { Text("Assist chip") },
        leadingIcon = {
            Icon(
                Icons.Filled.Add,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )
}


@Preview
@Composable
fun FilterChipExample() {
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        onClick = { selected = !selected },
        label = {
            Text("Filter chip")
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    Icons.Filled.Done,
                    contentDescription = "Done icon",
                    Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        }
    )
}


@Preview
@Composable
fun InputChipExample(
    text: String = "Input chip",
    onDismiss: () -> Unit = {}
) {
    var enable by remember { mutableStateOf(true) }
    if (!enable) return

    InputChip(
        onClick = {
            onDismiss()
            enable = !enable
        },
        label = { Text(text) },
        selected = enable,
        avatar = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "Avatar",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        },
        trailingIcon = {
            Icon(
                Icons.Filled.Close,
                contentDescription = "Close",
                Modifier.size(InputChipDefaults.AvatarSize)
            )
        }
    )
}

@Preview
@Composable
fun SuggestionChipExample() {
    SuggestionChip(
        onClick = { Log.d("SuggestionChipExample", "SuggestionChip clicked") },
        label = {
            Text("Suggestion chip")
        }
    )
}