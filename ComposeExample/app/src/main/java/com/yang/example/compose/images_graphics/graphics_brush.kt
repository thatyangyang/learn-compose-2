package com.yang.example.compose.images_graphics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yang.example.compose.R


val colors = listOf(Color.Red, Color.Blue)

@Preview
@Composable
fun BrushGradientExample1() {
    val brush = Brush.horizontalGradient(colors)
    Column(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier.size(200.dp),
            onDraw = {
                drawCircle(brush = brush)
            }
        )
    }
}

@Preview
@Composable
fun BrushGradientAllExample() {
    LazyColumn {
        item {
            val brush = Brush.horizontalGradient(colors)
            Column(modifier = Modifier.fillMaxSize()) {
                Canvas(
                    modifier = Modifier.size(200.dp),
                    onDraw = {
                        drawRect(brush = brush)
                    }
                )
            }
        }

        item {
            val brush = Brush.verticalGradient(colors)
            Column(modifier = Modifier.fillMaxSize()) {
                Canvas(
                    modifier = Modifier.size(200.dp),
                    onDraw = {
                        drawRect(brush = brush)
                    }
                )
            }
        }
        item {
            val brush = Brush.linearGradient(colors)
            Column(modifier = Modifier.fillMaxSize()) {
                Canvas(
                    modifier = Modifier.size(200.dp),
                    onDraw = {
                        drawRect(brush = brush)
                    }
                )
            }
        }
        item {
            val brush = Brush.sweepGradient(colors)
            Column(modifier = Modifier.fillMaxSize()) {
                Canvas(
                    modifier = Modifier.size(200.dp),
                    onDraw = {
                        drawRect(brush = brush)
                    }
                )
            }
        }
        item {
            val brush = Brush.radialGradient(colors)
            Column(modifier = Modifier.fillMaxSize()) {
                Canvas(
                    modifier = Modifier.size(200.dp),
                    onDraw = {
                        drawRect(brush = brush)
                    }
                )
            }
        }
    }
}


@Preview
@Composable
fun BrushGradientExample2() {
    val colorStops = arrayOf(
        0.0f to Color.Yellow,
        0.2f to Color.Red,
        1f to Color.Blue
    )

    val brush = Brush.horizontalGradient(colorStops = colorStops)
    Column(modifier = Modifier.fillMaxSize()) {
        Canvas(
            modifier = Modifier.size(200.dp),
            onDraw = {
                drawRect(brush = brush)
            }
        )
    }
}


@Preview
@Composable
fun BrushGradientRepeatModeExample() {
    val colors = listOf(Color.Yellow, Color.Red, Color.Blue)
    val tileSize = with(LocalDensity.current) {
        50.dp.toPx()
    }
    LazyColumn {
        item {
            val brush =
                Brush.horizontalGradient(
                    colors = colors,
                    endX = tileSize,
                    tileMode = TileMode.Repeated
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

        item {
            val brush =
                Brush.horizontalGradient(
                    colors = colors,
                    endX = tileSize,
                    tileMode = TileMode.Clamp
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
        item {
            val brush =
                Brush.horizontalGradient(
                    colors = colors,
                    endX = tileSize,
                    tileMode = TileMode.Mirror
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
        item {
            val brush =
                Brush.horizontalGradient(
                    colors = colors,
                    endX = tileSize,
                    tileMode = TileMode.Decal
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
    }
}

@Preview
@Composable
fun BrushGradientExample4() {
    val listColors = listOf(Color.Yellow, Color.Red, Color.Blue)

    val customBrush = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                return LinearGradientShader(
                    colors = listColors,
                    from = Offset.Zero,
                    to = Offset(size.width / 4f, 0f),
                    tileMode = TileMode.Mirror
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .requiredSize(400.dp)
            .background(customBrush)
    )
}

@Preview
@Composable
fun BrushGradientExample5() {
    val listColors = listOf(Color(0xFF2be4dc), Color(0xFF243484))

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.radialGradient(listColors))
    )
}

@Preview
@Composable
fun BrushGradientExample5_2() {
    val listColors = listOf(Color(0xFF2be4dc), Color(0xFF243484))

    val customBrush = remember {
        object : ShaderBrush() {
            override fun createShader(size: Size): Shader {
                val biggerDimension = maxOf(size.height, size.width)
                return RadialGradientShader(
                    colors = listColors,
                    center = size.center,
                    radius = biggerDimension / 2f,
                    colorStops = listOf(0f, 0.95f)
                )
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(customBrush)
    )
}


@Preview
@Composable
fun BrushGradientExampleImage() {
    val imageBrush = ShaderBrush(ImageShader(ImageBitmap.imageResource(R.drawable.dog)))

    Column {
        Box(
            modifier = Modifier
                .requiredSize(200.dp)
                .background(imageBrush)
        )

        Text(
            text = "Hello Yang", style = TextStyle(
                brush = imageBrush,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp
            ), modifier = Modifier.size(200.dp, 100.dp)
        )

        Canvas(
            onDraw = { drawCircle(imageBrush) },
            modifier = Modifier.size(200.dp)
        )
    }
}
