package com.yang.example.compose.touch_input

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Composable
@Preview
private fun ScrollBoxes() {
    val state = rememberScrollState()
    LaunchedEffect(Unit) {
        delay(2000)
//        state.animateScrollTo(500)
    }
    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .size(200.dp)
            .verticalScroll(state)
    ) {
        repeat(1000) {
            Text("Item $it", modifier = Modifier.padding(2.dp))
        }
    }
}

@Composable
@Preview
private fun ScrollableSample() {
    var offset by remember { mutableFloatStateOf(0f) }
    Box(
        Modifier
            .size(200.dp)
            .scrollable(
                orientation = Orientation.Vertical,
                state = rememberScrollableState { delta ->
                    offset += delta
                    delta
                }
            )
            .background(Color.LightGray)
    ) {
        Text(offset.toString(), modifier = Modifier.align(Alignment.Center))
    }
}


@Composable
@Preview
private fun AutomaticNestedScroll() {
    val gradient = Brush.verticalGradient(0f to Color.Gray, 1000f to Color.White)
    Box(
        modifier = Modifier
            .background(Color.LightGray)
            .verticalScroll(rememberScrollState())
            .padding(32.dp)
    ) {
        Column() {
            repeat(6) {
                Box(
                    modifier = Modifier
                        .height(128.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        "Scroll here",
                        modifier = Modifier
                            .border(12.dp, Color.DarkGray)
                            .background(brush = gradient)
                            .padding(20.dp)
                            .height(150.dp)
                    )
                }
            }
        }
    }
}

val nestedScrollConnection = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        return super.onPreScroll(available, source)
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        return super.onPostScroll(consumed, available, source)
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        return super.onPreFling(available)
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        return super.onPostFling(consumed, available)
    }
}


@Preview
@Composable
private fun NestedScrollSample() {
    ImageResizeOnScrollExample(minImageSize = 0.dp)
}

@Preview
@Composable
private fun ImageResizeOnScrollExample(
    modifier: Modifier = Modifier,
    maxImageSize: Dp = 300.dp,
    minImageSize: Dp = 100.dp
) {
    var currentImageSize by remember { mutableStateOf(maxImageSize) }
    var imageScale by remember { mutableFloatStateOf(1f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newImageSize = currentImageSize + delta.dp
                val previousImageSize = currentImageSize

                currentImageSize = newImageSize.coerceIn(
                    minImageSize,
                    maxImageSize
                )
                val consumed = currentImageSize - previousImageSize

                imageScale = currentImageSize / maxImageSize
                return Offset(0f, consumed.value)
            }
        }
    }

    Box(Modifier.nestedScroll(nestedScrollConnection)) {
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(15.dp)
                .offset {
                    IntOffset(0, currentImageSize.roundToPx())
                }
        ) {
            items(100, key = { it }) {
                Text(text = "Item $it", style = MaterialTheme.typography.bodyLarge)
            }
        }

        Image(
            painter = ColorPainter(Color.Red),
            contentDescription = "",
            Modifier
                .size(maxImageSize)
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    scaleX = imageScale
                    scaleY = imageScale
                    translationY = -(maxImageSize - currentImageSize).toPx() / 2f
                })
    }
}







