package com.yang.example.compose.images_graphics

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun DrawWithContentExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawWithContent {
                drawContent()
                drawCircle(Color.Cyan, radius = size.minDimension / 4f)
//                drawContent()
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp)
                .background(Color.Red)
        )
    }
}

@Preview
@Composable
fun DrawWithContentExample2() {
    var pointerOffset by remember { mutableStateOf(Offset(0f, 0f)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput("dragging") {
                detectDragGestures { change, dragAmount ->
                    pointerOffset += dragAmount
                }
            }
            .onSizeChanged {
                pointerOffset = Offset(it.width / 2f, it.height / 2f)
            }
            .drawWithContent {
                drawContent()
                drawRect(
                    Brush.radialGradient(
                        listOf(Color.Transparent, Color.Black),
                        center = pointerOffset,
                        radius = 100.dp.toPx()
                    )
                )
            }
    ) {
        Text("adbc".repeat(1000))
    }
}


@Preview
@Composable
fun DrawBehindExample() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind() {
                drawCircle(Color.Cyan, radius = size.minDimension / 4f)
            }
    ) {
        Text("adbc".repeat(1000))
    }
}

@Preview
@Composable
fun DrawBehindExample2() {
    Box(modifier = Modifier.padding(top = 32.dp)) {
        Text(
            "Hello Compose!", modifier = Modifier
                .drawBehind {
                    drawRoundRect(Color(0xFFBBAACC), cornerRadius = CornerRadius(10.dp.toPx()))
                }
                .padding(4.dp))
    }
}

@Preview
@Composable
fun DrawWithCacheExample() {
    Box(modifier = Modifier.padding(top = 32.dp)) {
        Text(
            "Hello Compose!", modifier = Modifier
                .drawWithCache {
                    val brush = Brush.linearGradient(
                        listOf(
                            Color(0xFF9E82F0),
                            Color(0xFF42A5F5)
                        )
                    )

                    onDrawBehind {
                        drawRoundRect(brush, cornerRadius = CornerRadius(10.dp.toPx()))
                    }
                }
                .padding(4.dp))
    }
}


