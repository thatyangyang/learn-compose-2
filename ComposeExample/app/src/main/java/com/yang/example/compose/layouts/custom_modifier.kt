package com.yang.example.compose.layouts

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.constrainWidth
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.offset
import com.yang.example.compose.R


fun Modifier.myClip(shape: Shape): Modifier {
    return graphicsLayer(shape = shape, clip = true)
}

fun Modifier.mBackgraoud(color: Color) = background(color)
    .padding(16.dp)
    .clip(RoundedCornerShape(8.dp))


@Preview
@Composable
fun CustomDemoPreview() {
    Image(
        painter = painterResource(R.drawable.hero),
        contentDescription = "",
        Modifier
            .padding(10.dp)
            .size(100.dp)
            .myClip(CircleShape)
    )
}

@Preview
@Composable
fun CustomDemoPreview2() {
    Image(
        painter = painterResource(R.drawable.hero),
        contentDescription = "",
        Modifier.mBackgraoud(Color.Red)
    )
}

@Composable
fun Modifier.fade(enable: Boolean): Modifier {
    val alpha by animateFloatAsState(if (enable) 0.5f else 1.0f)
    return this then Modifier.graphicsLayer {
        this.alpha = alpha
    }
}

@Preview
@Composable
fun FadePreview() {
    Column {
        val enable = remember {
            mutableStateOf(false)
        }
        Button(onClick = {
            enable.value = !enable.value
        }) { }

        Image(
            painter = painterResource(R.drawable.hero),
            contentDescription = "",
            Modifier
                .padding(10.dp)
                .size(50.dp)
                .fade(enable.value)
        )
    }
}

@Composable
fun Modifier.fadeBackground(): Modifier {
    val color = LocalContentColor.current
    return this then Modifier.background(color.copy(alpha = 0.5f))
}

@Preview
@Composable
fun MyScreen() {
    CompositionLocalProvider(LocalContentColor provides Color.Green) {
        val fadeBackgroundModifier = Modifier.fadeBackground()
        Box(modifier = fadeBackgroundModifier.size(200.dp))
        CompositionLocalProvider(LocalContentColor provides Color.Blue) {
            // green
            Box(modifier = fadeBackgroundModifier.size(100.dp))
        }
    }
}

/***  ***/
private class CircleNode(var color: Color) : DrawModifierNode, Modifier.Node() {
    override fun ContentDrawScope.draw() {
        drawCircle(color)
    }
}

// Node -> Element(:Modifier)
private data class CircleElement(val color: Color) : ModifierNodeElement<CircleNode>() {
    override fun create() = CircleNode(color)

    override fun update(node: CircleNode) {
        node.color = color
    }
}

fun Modifier.circle(color: Color) = this then CircleElement(color)

@Preview
@Composable
fun CircleNodePreview() {
    Box(
        modifier = Modifier
            .circle(Color.Red)
            .size(100.dp)
    )
}

/***  ***/

fun Modifier.fixedPadding() = this then FixedPaddingElement

data object FixedPaddingElement : ModifierNodeElement<FixedPaddingNode>() {
    override fun create() = FixedPaddingNode()
    override fun update(node: FixedPaddingNode) {}
}

class FixedPaddingNode : LayoutModifierNode, Modifier.Node() {
    private val PADDING = 16.dp

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val paddingPx = PADDING.roundToPx()
        val horizontal = paddingPx * 2
        val vertical = paddingPx * 2

        val placeable = measurable.measure(constraints.offset(-horizontal, -vertical))

        val width = constraints.constrainWidth(placeable.width + horizontal)
        val height = constraints.constrainWidth(placeable.height + vertical)

        return layout(width, height) {
            placeable.place(paddingPx, paddingPx)
        }
    }
}

@Preview
@Composable
fun FixedPaddingNodePreview() {
    Box(
        modifier = Modifier
            .fixedPadding()
            .background(Color.Red)
            .size(100.dp)
    )
}


//@Sampled
