package com.yang.example.compose.animation

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun CustomizeAnimationExample() {
    var enable by remember { mutableStateOf(false) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (enable) 1f else 0.5f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "alpha"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    this.alpha = alpha
                }
                .background(Color.Red)
        )

        Button(onClick = { enable = !enable }) {
            Text("button")
        }
    }
}


@Preview
@Composable
fun CustomizePhysicsBasedBouncyAnimationExample() {
    var enable by remember { mutableStateOf(false) }
    /*val offset: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "alpha"
    )*/
    val offset1: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "alpha"
    )
    val offset2: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioLowBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "alpha"
    )
    val offset3: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "alpha"
    )
    val offset4: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "alpha"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset1, 40.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset2, 40.dp.toPx() + 100.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset3, 40.dp.toPx() + 200.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset4, 40.dp.toPx() + 300.dp.toPx())
                    )
                }
        )

        Button(
            onClick = { enable = !enable },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("button")
        }
    }
}


@Preview
@Composable
fun CustomizePhysicsBasedStiffnessAnimationExample() {
    var enable by remember { mutableStateOf(false) }
    val offset1: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "alpha"
    )
    val offset2: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMediumLow,
        ),
        label = "alpha"
    )
    val offset3: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessMedium,
        ),
        label = "alpha"
    )
    val offset4: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioNoBouncy,
            stiffness = Spring.StiffnessHigh,
        ),
        label = "alpha"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset1, 40.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset2, 40.dp.toPx() + 100.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset3, 40.dp.toPx() + 200.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset4, 40.dp.toPx() + 300.dp.toPx())
                    )
                }
        )

        Button(
            onClick = { enable = !enable },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("button")
        }
    }
}


@Preview
@Composable
fun CustomizeTweenEasingAnimationExample() {
    var enable by remember { mutableStateOf(false) }
    val offset1: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = tween(durationMillis = 300, easing = FastOutSlowInEasing),
        label = "alpha"
    )
    val offset2: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing),
        label = "alpha"
    )
    val offset3: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = tween(durationMillis = 300, easing = FastOutLinearInEasing),
        label = "alpha"
    )
    val offset4: Float by animateFloatAsState(
        targetValue = if (enable) 400f else 0f,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "alpha"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset1, 40.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset2, 40.dp.toPx() + 100.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset3, 40.dp.toPx() + 200.dp.toPx())
                    )

                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset(40.dp.toPx() + offset4, 40.dp.toPx() + 300.dp.toPx())
                    )
                }
        )

        Button(
            onClick = { enable = !enable },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("button")
        }
    }
}


@Preview
@Composable
fun CustomizeKeyframeAnimationExample() {
    var enable by remember { mutableStateOf(false) }
    val offset: Offset by animateOffsetAsState(
        targetValue = if (enable) Offset(300f, 300f) else Offset(0f, 0f),
        animationSpec = keyframes {
            durationMillis = 6000
            Offset(0f, 0f) at 0
            Offset(150f, 200f) atFraction 0.5f
            Offset(0f, 100f) atFraction 0.7f
        },
        label = "alpha"
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    drawCircle(
                        color = Color.Green,
                        radius = 40.dp.toPx(),
                        center = Offset((size.width / 2.0f), 40.dp.toPx()).plus(
                            offset
                        )
                    )
                }
        )

        Button(
            onClick = { enable = !enable },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("button")
        }
    }
}


