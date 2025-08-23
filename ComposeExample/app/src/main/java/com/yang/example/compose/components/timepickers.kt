package com.yang.example.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun convertMillisToTime(millis: Long): String {
    val formatter = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return formatter.format(Date(millis))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TimePickerExample() {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )
    var selected by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    Column {
        TimePicker(state = timePickerState)

        Button(onClick = {}) {
            Text("Dismiss picker")
        }
        Button(onClick = {
            selected = true
            selectedTime = Pair(timePickerState.hour, timePickerState.minute)
        }) {
            Text("Confirm selection")
        }

        if (selected) {
            Text("Selected time: ${selectedTime?.first}:${timePickerState?.minute}")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun TimeInputExample() {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )
    var selectedTime by remember { mutableStateOf<TimePickerState?>(null) }

    Column {
        TimeInput(state = timePickerState)

        Button(onClick = {}) {
            Text("Dismiss picker")
        }
        Button(onClick = {
            selectedTime = timePickerState
        }) {
            Text("Confirm selection")
        }

        if (selectedTime != null) {
            val cal = Calendar.getInstance()
            cal.set(Calendar.HOUR_OF_DAY, selectedTime!!.hour)
            cal.set(Calendar.MINUTE, selectedTime!!.minute)
            cal.isLenient = false
            Text("Selected time: ${convertMillisToTime(cal.timeInMillis)}")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun DialWithDialogExample() {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    TimePickerDialog(onDismiss = {}, onConfirm = {}) {
        TimePicker(state = timePickerState)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimePickerDialog(
    onDismiss: () -> Unit, onConfirm: () -> Unit, content: @Composable () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("cancel")
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text("ok")
            }
        }, text = { content() }
    )
}


@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTimePickerExample() {
    val currentTime = Calendar.getInstance()

    val timePickerState = rememberTimePickerState(
        initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
        initialMinute = currentTime.get(Calendar.MINUTE),
        is24Hour = true
    )

    var showDial by remember { mutableStateOf(true) }

    val toggleIcon = if (showDial) {
        Icons.Filled.Edit
    } else {
        Icons.Filled.DateRange
    }

    AdvancedTimePickerDialog(
        onDismiss = {},
        onConfirm = {},
        toggle = {
            IconButton(
                onClick = { showDial = !showDial }
            ) {
                Icon(toggleIcon, "")
            }
        }) {
        if (showDial) {
            TimePicker(state = timePickerState)
        } else {
            TimeInput(state = timePickerState)
        }
    }
}

@Composable
fun AdvancedTimePickerDialog(
    title: String = "Select Time",
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
    toggle: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            shape = MaterialTheme.shapes.extraLarge,
            tonalElevation = 6.dp,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .height(IntrinsicSize.Min)
                .background(
                    shape = MaterialTheme.shapes.extraLarge,
                    color = MaterialTheme.colorScheme.surface
                )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    text = title,
                    style = MaterialTheme.typography.labelMedium
                )
                content()
                Row(
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                ) {
                    toggle()
                    Spacer(
                        modifier = Modifier
                            .weight(1f)
                    )
                    TextButton(onClick = onDismiss) {
                        Text("cancel")
                    }
                    TextButton(onClick = onConfirm) {
                        Text("ok")
                    }
                }
            }
        }
    }
}

