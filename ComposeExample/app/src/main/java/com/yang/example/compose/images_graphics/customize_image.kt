package com.yang.example.compose.images_graphics

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.yang.example.compose.R


val imageModifier = Modifier
    .size(120.dp)
    .border(BorderStroke(1.dp, Color.Black))
    .background(Color.Yellow)

@Preview
@Composable
fun ContentScaleExample() {
    Column {
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = imageModifier
        )
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = imageModifier
        )
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.FillHeight,
            modifier = imageModifier
        )
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.FillWidth,
            modifier = imageModifier
        )
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = imageModifier
        )
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Inside,
            modifier = imageModifier
        )
    }
}


@Preview
@Composable
fun ClipExample() {
    Column {
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(16.dp))
        )

        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .clip(SquashedOval())
        )
    }
}

class SquashedOval : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val path = Path().apply {
            addOval(
                Rect(
                    left = size.width / 4,
                    top = 0f,
                    right = size.width * 3 / 4f,
                    bottom = size.height
                )
            )
        }
        return Outline.Generic(path = path)
    }
}


@Preview
@Composable
fun BorderExample() {
    val rainbowColorsBrush = remember {
        Brush.sweepGradient(
            listOf(
                Color(0xFF9575CD),
                Color(0xFFBA68C8),
                Color(0xFFE57373),
                Color(0xFFFFB74D),
                Color(0xFFFFF176),
                Color(0xFFAED581),
                Color(0xFF4DD0E1),
                Color(0xFF9575CD)
            )
        )
    }
    val borderWidth = 4.dp

    Column {
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .border(
                    BorderStroke(borderWidth, Color.Yellow),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        )

        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(200.dp)
                .border(
                    BorderStroke(borderWidth, rainbowColorsBrush),
                    CircleShape
                )
                .padding(borderWidth)
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
fun AspectRatioExample() {
    Column {
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            modifier = Modifier
                .size(150.dp)
                .background(Color.Red)
                .aspectRatio(16f / 9f)
        )
    }
}

@Preview
@Composable
fun ColorFilterExample() {
    Column(modifier = Modifier.padding(top = 30.dp)) {
        Image(
            painter = painterResource(R.drawable.baseline_shopping_cart_24),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Yellow)
        )

        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            colorFilter = ColorFilter.tint(Color.Green, blendMode = BlendMode.Darken)
        )
    }
}

val contrast = 2f // 0f..10f (1 should be default)
val brightness = -180f // -255f..255f (0 should be default)
val colorMatrix = floatArrayOf(
    contrast, 0f, 0f, 0f, brightness,
    0f, contrast, 0f, 0f, brightness,
    0f, 0f, contrast, 0f, brightness,
    0f, 0f, 0f, 1f, 0f
)

@Preview
@Composable
fun ColorFilterExample2() {
    Column(modifier = Modifier.padding(top = 30.dp)) {
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix()
                    .apply { setToSaturation(0f) })
        )

        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix(colorMatrix)
            )
        )
    }
}

@Preview
@Composable
fun ColorFilterExample3() {
    val colorMatrix = floatArrayOf(
        -1f, 0f, 0f, 0f, 255f,
        0f, -1f, 0f, 0f, 255f,
        0f, 0f, -1f, 0f, 255f,
        0f, 0f, 0f, 1f, 0f
    )

    Column(modifier = Modifier.padding(top = 30.dp)) {
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            colorFilter = ColorFilter.colorMatrix(
                ColorMatrix(colorMatrix)
            )
        )
    }
}

@Preview
@Composable
fun BlurExample() {
    Column(modifier = Modifier.padding(top = 30.dp)) {
        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .blur(
                    radiusX = 10.dp, radiusY = 10.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                )
        )

        Image(
            painter = painterResource(R.drawable.dog),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(150.dp)
                .blur(
                    radiusX = 10.dp, radiusY = 10.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded
                )
                .clip(RoundedCornerShape(8.dp))
        )
    }
}

