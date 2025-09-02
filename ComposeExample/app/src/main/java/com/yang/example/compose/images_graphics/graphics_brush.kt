package com.yang.example.compose.images_graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun BrushGradientExample1() {
    val colors = listOf(Color.Red, Color.Blue)
    val brush = Brush.horizontalGradient(colors)
    Canvas(
        modifier = Modifier.size(200.dp),
        onDraw = {
            drawCircle(brush = brush)
        }
    )

    Column(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier.size(200.dp),
            onDraw = {
                drawRect(brush = brush)
            }
        )
    }
}