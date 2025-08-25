package com.yang.example.compose.images_graphics

import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PointMode
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.yang.example.compose.R


@Composable
@Preview
fun DrawModifierExample() {
    Spacer(
        Modifier
            .fillMaxSize()
            .drawBehind {
                val canvasQuadrantSize = size / 2f
                drawRect(
                    color = Color.Magenta,
                    size = canvasQuadrantSize
                )
            }
            .drawWithContent {

            })
}

@Composable
@Preview
fun CanvasExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size / 2f
        drawRect(
            color = Color.Magenta,
            size = canvasQuadrantSize
        )
    }
}

@Preview
@Composable
fun CoordinateSystemExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasQuadrantSize = size
        drawLine(
            start = Offset(canvasQuadrantSize.width, 0f),
            end = Offset(0f, canvasQuadrantSize.height),
            color = Color.Blue
        )
    }
}

/** Basic transformations: scale, translate, rotate, inset **/

@Preview
@Composable
fun ScaleExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
//        drawCircle(Color.Blue, radius = 20.dp.toPx())
        scale(scaleX = 10f, scaleY = 15f) {
            drawCircle(Color.Blue, radius = 10.dp.toPx())
        }
    }
}

@Preview
@Composable
fun TranslateExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        translate(left = 100f, top = -300f) {
            drawCircle(Color.Blue, radius = 100.dp.toPx())
        }
    }
}

@Preview
@Composable
fun RotateExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        rotate(degrees = 45F) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}

@Preview
@Composable
fun InsetExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        /*drawRect(
            color = Color.Red,
            topLeft = Offset(0F, 0F),
            size = size
        )*/
        inset(horizontal = 50F, vertical = 30f) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(0F, 0F),
                size = size
            )
        }
    }
}

@Preview
@Composable
fun MultiTransformExample() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        withTransform(
            {
                translate(left = size.width / 2F)
                rotate(degrees = 45F)
            }
        ) {
            drawRect(
                color = Color.Red,
                topLeft = Offset(x = size.width / 3F, y = size.height / 3F),
                size = size / 3F
            )
        }
    }
}

/** Common drawing operations: text, image, shape, path **/

@Preview
@Composable
fun DrawTextExample() {
    val textMeasurer = rememberTextMeasurer()
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawText(textMeasurer, "Hello")
    }
}

@Preview
@Composable
fun DrawTextExample2() {
    val longstr = stringResource(R.string.lorem_ipsum)
    val textMeasurer = rememberTextMeasurer()
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                val measuredText = textMeasurer.measure(
                    AnnotatedString(longstr),
//                    constraints = Constraints.fixedWidth((size.width * 2F / 3F).toInt()),
                    constraints = Constraints.fixed(
                        (size.width / 3f).toInt(),
                        (size.height / 3F).toInt()
                    ),
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(fontSize = 18.sp)
                )
                onDrawBehind {
                    drawRect(Color.Red, size = measuredText.size.toSize())
                    drawText(measuredText)
                }
            }
    )
}

@Preview
@Composable
fun DrawImageExample() {
    val dogImage = ImageBitmap.imageResource(R.drawable.dog)
    Canvas(modifier = Modifier.fillMaxSize()) {
        drawImage(dogImage)
    }
}

@Preview
@Composable
fun DrawShapeCircleExample() {
    val purpleColor = Color(0xFFD87BE7)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drawCircle(purpleColor)
    }
}

@Preview
@Composable
fun DrawShapeRectExample() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drawRect(purpleColor)
    }
}

@Preview
@Composable
fun DrawShapeRoundedRectExample() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drawRoundRect(purpleColor, cornerRadius = CornerRadius(36.dp.toPx(), 36.dp.toPx()))
    }
}

@Preview
@Composable
fun DrawShapeLineExample() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drawLine(purpleColor, start = Offset(0f, 0f), end = Offset(size.width, size.height))
    }
}

@Preview
@Composable
fun DrawShapeOvalExample() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drawOval(purpleColor)
    }
}

@Preview
@Composable
fun DrawShapeArcExample() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        drawArc(purpleColor, startAngle = 0f, sweepAngle = 270f, useCenter = true)
    }
}

@Preview
@Composable
fun DrawShapePointsExample() {
    val purpleColor = Color(0xFFBA68C8)
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val points = listOf(
            Offset(size.width / 3f, size.height / 3f),
            Offset(size.width / 2f, size.height / 2f),
            Offset(size.width * 2f / 3f, size.height * 2 / 3f)
        )
        drawPoints(
            points,
            PointMode.Points,
            purpleColor,
            strokeWidth = 10.dp.toPx(),
            cap = StrokeCap.Round
        )
    }
}

@Preview
@Composable
fun DrawShapePathExample() {
    val purpleColor = Color(0xFFBA68C8)
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .drawWithCache {
                val path = Path()
                path.moveTo(0f, 0f)
                path.lineTo(size.width / 2f, size.height / 2f)
                path.lineTo(size.width, size.height / 2f)
                path.close()
                onDrawBehind {
                    drawPath(path = path, color = purpleColor, style = Stroke(width = 10f))
                }
            }
    )
}

/** Accessing Canvas object **/


@Preview
@Composable
fun DrawCanvasExample() {
    val purpleColor = Color(0xFFBA68C8)
    val drawable = ShapeDrawable(OvalShape())
    Spacer(
        modifier = Modifier
            .fillMaxSize()
            .drawWithContent {
                drawIntoCanvas { canvas ->
                    drawable.setBounds(0, 0, size.width.toInt(), size.height.toInt())
                    drawable.draw(canvas.nativeCanvas)
                }
            }
    )
}