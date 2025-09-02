package com.yang.example.compose.images_graphics

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.DrawModifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.layer.drawLayer
import androidx.compose.ui.graphics.rememberGraphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.example.compose.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream


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

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModifierGraphicsLayerModifierScale() {
    Column(modifier = Modifier.fillMaxSize()) {
        var scaleX by remember { mutableFloatStateOf(1f) }
        var scaleY by remember { mutableFloatStateOf(1f) }
        Text(text = "scaleX")
        Slider(
            value = scaleX,
            onValueChange = { scaleX = it },
            valueRange = 0f..2f
        )
        Text(text = "scaleY")
        Slider(
            value = scaleY,
            onValueChange = { scaleY = it },
            valueRange = 0f..2f
        )

        Image(
            painter = painterResource(R.drawable.sunset),
            contentDescription = "",
            modifier = Modifier.graphicsLayer {
                this.scaleX = scaleX
                this.scaleY = scaleY
            }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModifierGraphicsLayerModifierTranslate() {
    Column(modifier = Modifier.fillMaxSize()) {
        var translateX by remember { mutableFloatStateOf(0f) }
        var translateY by remember { mutableFloatStateOf(0f) }

        val density = LocalDensity.current
        val px = with(density) { 200.dp.toPx() }

        Text(text = "translateX")
        Slider(
            value = translateX,
            onValueChange = { translateX = it },
            valueRange = 0f..360f
        )
        Text(text = "translateY")
        Slider(
            value = translateY,
            onValueChange = { translateY = it },
            valueRange = 0f..360f
        )

        Image(
            painter = painterResource(R.drawable.sunset),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .graphicsLayer {
                    this.translationX = translateX
                    this.translationY = translateY
                }
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModifierGraphicsLayerModifierRotate() {
    Column(modifier = Modifier.fillMaxSize()) {
        var rotateX by remember { mutableFloatStateOf(0f) }
        var rotateY by remember { mutableFloatStateOf(0f) }
        var rotateZ by remember { mutableFloatStateOf(0f) }

        Text(text = "rotateX")
        Slider(
            value = rotateX,
            onValueChange = { rotateX = it },
            valueRange = 0f..360f
        )
        Text(text = "rotateY")
        Slider(
            value = rotateY,
            onValueChange = { rotateY = it },
            valueRange = 0f..360f
        )
        Text(text = "rotateZ")
        Slider(
            value = rotateZ,
            onValueChange = { rotateZ = it },
            valueRange = 0f..360f
        )


        Image(
            painter = painterResource(R.drawable.sunset),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .graphicsLayer {
                    this.transformOrigin = TransformOrigin(0f, 0f)
                    this.rotationX = rotateX
                    this.rotationY = rotateY
                    this.rotationZ = rotateZ
                }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModifierGraphicsLayerModifierClipShape() {
    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
                .size(200.dp)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                }
                .background(Color(0xFFDC3470))
        ) {
            Text(text = "Hi Yang Yang", modifier = Modifier.align(Alignment.Center))
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(0xFF99AA20))
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModifierGraphicsLayerModifierClipShape2() {
    Column(modifier = Modifier.padding(16.dp)) {
        Box(
            modifier = Modifier
//                .clip(RectangleShape)
                .size(200.dp)
                .graphicsLayer {
                    clip = true
                    shape = CircleShape
                    translationY = 50.dp.toPx()
                }
                .background(Color(0xFFDC3470))
        ) {
            Text(text = "Hi Yang Yang", modifier = Modifier.align(Alignment.Center))
        }

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .background(Color(0xFF99AA20))
        )
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModifierGraphicsLayerModifierAlpha() {
    Column(modifier = Modifier.fillMaxSize()) {
        var alpha by remember { mutableFloatStateOf(1f) }

        Text(text = "alpha")
        Slider(
            value = alpha,
            onValueChange = { alpha = it },
            valueRange = 0f..1f
        )

        Image(
            painter = painterResource(R.drawable.sunset),
            contentDescription = "",
            modifier = Modifier
                .size(200.dp, 200.dp)
                .graphicsLayer {
                    this.alpha = alpha
                }
        )
    }
}

val bitmapPath = "/data/user/0/com.yang.example.compose/files/graphicBitmap.png"

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModifierGraphicsLayerModifierCreateBitmap() {
    val coroutineScope = rememberCoroutineScope()
    val graphicsLayer = rememberGraphicsLayer()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawWithContent {
                graphicsLayer.record {
                    this@drawWithContent.drawContent()
                }
                drawLayer(graphicsLayer)
            }
            .clickable {
                coroutineScope.launch(Dispatchers.IO) {
                    val bitmap = graphicsLayer.toImageBitmap()
                    val file = File(bitmapPath).apply {
                        if (!this.exists()) {
                            this.createNewFile()
                        }
                    }
                    FileOutputStream(file).use { out ->
                        bitmap.asAndroidBitmap().compress(Bitmap.CompressFormat.PNG, 100, out)
                    }
                }
            }
            .background(Color.White)
    ) {
        ModifierGraphicsLayerModifierClipShape()
    }
}


class FlipDrawModifier : DrawModifier {
    override fun ContentDrawScope.draw() {
        this@draw.scale(1f, -1f) {
            this@draw.drawContent()
        }
    }

}

fun Modifier.flip() = this.then(FlipDrawModifier())


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ModifierGraphicsLayerModifierDrawModifier() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(text = "Hello, Yang!", modifier = Modifier
            .flip()
            .align(Alignment.Center))
    }
}

