package com.yang.example.compose.touch_input

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview


@Preview
@Composable
private fun TransformableSample() {
    var scale by remember { mutableFloatStateOf(1f) }
    var rotation by remember { mutableFloatStateOf(0f) }
    var offset by remember { mutableStateOf(Offset.Zero) }

    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        println("zoomChange=$zoomChange, offsetChange=$offsetChange, rotationChange=$rotationChange")
        scale *= zoomChange
        rotation += rotationChange
        offset += offsetChange
        println("scale=$scale, offset=$offset, rotation=$rotation")
    }

    Box(
        Modifier
            .graphicsLayer {
                translationX = offset.x
                translationY = offset.y
                scaleX = scale
                scaleY = scale
                rotationZ = rotation
            }
            .transformable(state = state)
            .background(Color.Blue)
            .fillMaxSize()
    ) {


    }
}