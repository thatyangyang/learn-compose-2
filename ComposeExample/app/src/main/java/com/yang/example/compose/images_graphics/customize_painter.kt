package com.yang.example.compose.images_graphics

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.yang.example.compose.R
import kotlin.math.roundToInt


class OverlayImagePainter constructor(
    private val image: ImageBitmap,
    private val imageOverlay: ImageBitmap,
    private val srcOffset: IntOffset = IntOffset.Zero,
    private val srcSize: IntSize = IntSize(image.width, image.height),
    private val overlaySize: IntSize = IntSize(imageOverlay.width, imageOverlay.height),
) : Painter() {
    private val size: IntSize = validateSize(srcOffset, srcSize)

    override val intrinsicSize: Size = size.toSize()

    override fun DrawScope.onDraw() {
        drawImage(
            image, srcOffset, srcSize, dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            )
        )

        drawImage(
            imageOverlay, srcOffset, overlaySize, dstSize = IntSize(
                this@onDraw.size.width.roundToInt(),
                this@onDraw.size.height.roundToInt()
            ), blendMode = BlendMode.Overlay
        )
    }

    private fun validateSize(srcOffset: IntOffset, srcSize: IntSize): IntSize {
        require(
            srcOffset.x >= 0 &&
                    srcOffset.y >= 0 &&
                    srcSize.width >= 0 &&
                    srcSize.height >= 0 &&
                    srcSize.width <= image.width &&
                    srcSize.height <= image.height
        )
        return srcSize
    }
}


@Preview
@Composable
fun PainterExample() {
    val rainbowImage = ImageBitmap.imageResource(id = R.drawable.rainbow)
    val dogImage = ImageBitmap.imageResource(id = R.drawable.dog)
    val customPainter = remember {
        OverlayImagePainter(dogImage, rainbowImage)
    }

    Column(modifier = Modifier.padding(top = 30.dp)) {
        Image(
            painter = customPainter,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .wrapContentSize()
        )

        Box(
            modifier =
                Modifier
                    .background(color = Color.Gray)
                    .padding(30.dp)
                    .background(color = Color.Yellow)
                    .paint(customPainter)
        ) { /** intentionally empty **/ }

    }
}