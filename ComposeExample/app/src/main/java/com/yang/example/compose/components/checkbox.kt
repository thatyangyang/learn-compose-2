package com.yang.example.compose.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun CheckboxMinimalExample() {
    var checked by remember { mutableStateOf(false) }

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Minimal Checkbox")
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }

        Text(
            if (checked) "Checked" else "Unchecked"
        )
    }
}


@Composable
@Preview
fun CheckboxParentExample() {
    val childCheckStates = remember { mutableStateListOf(false, false, false) }
    // parentState 不可观察, 但是 childCheckStates 变动, 会从这里刷新
    val parentState = when {
        childCheckStates.all { it } -> ToggleableState.On
        childCheckStates.none { it } -> ToggleableState.Off
        else -> ToggleableState.Indeterminate
    }
//    println("parentState $parentState")

    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Select all")
            TriStateCheckbox(
                state = parentState,
                onClick = {
                    val newValue = parentState != ToggleableState.On
                    childCheckStates.forEachIndexed { index, _ ->
                        childCheckStates[index] = newValue
                    }
                }
            )
        }

        childCheckStates.forEachIndexed { index, isChecked ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Option ${index + 1}")
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { childCheckStates[index] = it }
                )
            }
        }
    }
}






