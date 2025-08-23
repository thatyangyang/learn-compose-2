package com.yang.example.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SliderExample() {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Slider(
            value = sliderPosition,
            onValueChange = { sliderPosition = it },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            thumb = {
                Box(
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Thumb",
                        tint = Color.Yellow,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            steps = 3,
            valueRange = 0f..40f
        )
        Text(text = sliderPosition.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SliderAdvancedExample() {
    var sliderPosition by remember { mutableStateOf(0f..100f) }
    Column {
        RangeSlider(
            value = sliderPosition,
            onValueChange = { range -> sliderPosition = range },
            onValueChangeFinished = { },
            steps = 5,
            valueRange = 0f..100f
        )
        Text(text = sliderPosition.toString())
    }
}
