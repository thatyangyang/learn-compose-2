package com.yang.example.compose.animation

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.TargetBasedAnimation
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateRect
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay


@Preview
@Composable
fun AnimateAsStateExample1() {
    var enable by remember { mutableStateOf(true) }
    val alpha: Float by animateFloatAsState(if (enable) 1f else 0.5f, label = "alpha")

    LaunchedEffect(true) {
        while (true) {
            delay(3000)
            enable = !enable
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .clickable {
                enable = !enable
            }
            .graphicsLayer {
                this.alpha = alpha
            }
            .background(Color.Red))
}

enum class BoxState {
    Collapsed,
    Expanded
}

@Preview
@Composable
fun TransitionExample1() {
    var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(currentState, label = "box")

    val rect by transition.animateRect(label = "rectangle") { state ->
        when (state) {
            BoxState.Collapsed -> Rect(0f, 0f, 100f, 100f)
            BoxState.Expanded -> Rect(100f, 100f, 300f, 300f)
        }
    }
    val borderWidth by transition.animateDp(transitionSpec = {
        spring(visibilityThreshold = 0.00000f.dp)
    }, label = "border width") { state ->
        when (state) {
            BoxState.Collapsed -> 2.dp
            BoxState.Expanded -> 0.dp     // 为什么 0.dp 的时候，还能看到一点呢?
        }
    }

    val color by transition.animateColor(transitionSpec = {
        when {
            BoxState.Collapsed.isTransitioningTo(BoxState.Expanded) ->
                spring(stiffness = 50f)

            else ->
                tween(durationMillis = 500)
        }
    }, label = "color") { state ->
        when (state) {
            BoxState.Collapsed -> MaterialTheme.colorScheme.primary
            BoxState.Expanded -> MaterialTheme.colorScheme.background
        }
    }

    Box(Modifier.fillMaxSize()) {
        Box(
            Modifier
                .size(rect.width.dp, rect.height.dp)
                .offset(rect.left.dp, rect.top.dp)
                .border(width = borderWidth, color = Color.Red)
                .graphicsLayer {
                }
                .background(color)
        )


        Button(
            onClick = {
                currentState = if (currentState == BoxState.Collapsed) BoxState.Expanded
                else BoxState.Collapsed
            },
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text("button")
        }
    }
}

@Preview
@Composable
fun AnimatedVisibilityExample() {
    var selected by remember { mutableStateOf(false) }
    val transition = updateTransition(selected, "selected state")
    val borderColor by
    transition.animateColor { isSelected -> if (isSelected) Color.Magenta else Color.White }
    val elevation by transition.animateDp { isSelected -> if (isSelected) 10.dp else 2.dp }

    Surface(
        onClick = { selected = !selected },
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(2.dp, borderColor),
        shadowElevation = elevation
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Hello, yang")
            transition.AnimatedVisibility(
                visible = { it },
                enter = expandHorizontally(),
                exit = shrinkVertically()
            ) {
                Text(text = "It is fine today.")
            }

            transition.AnimatedContent {
                if (it) {
                    Text("selected")
                } else {
                    Icon(imageVector = Icons.Default.Phone, contentDescription = "phone")
                }
            }
        }
    }
}

@Composable
@Preview
fun AnimationBoxPreview() {
    var expanded by remember { mutableStateOf(false) }

    Box(Modifier.fillMaxSize()) {
        AnimationBox(if (expanded) BoxState.Expanded else BoxState.Collapsed)

        Button(
            onClick = { expanded = !expanded },
            modifier = Modifier.align(Alignment.BottomStart)
        ) {
            Text("button")
        }
    }
}

@Composable
fun AnimationBox(boxState: BoxState) {
    val transitionData = updateTransitionData(boxState)
    Box(
        modifier = Modifier
            .background(transitionData.color)
            .size(transitionData.size)
    )
}

private class TransitionData(
    color: androidx.compose.runtime.State<Color>,
    size: androidx.compose.runtime.State<Dp>
) {
    val color by color
    val size by size
}

@Composable
private fun updateTransitionData(boxState: BoxState): TransitionData {
    val transition = updateTransition(boxState, label = "box")

    val size = transition.animateDp(label = "size") { state ->
        when (state) {
            BoxState.Collapsed -> 64.dp
            BoxState.Expanded -> 128.dp
        }
    }

    val color = transition.animateColor(label = "color") { state ->
        when (state) {
            BoxState.Collapsed -> Color.Gray
            BoxState.Expanded -> Color.Red
        }
    }

    return remember(transition) { TransitionData(color, size) }
}

@Preview
@Composable
fun InfiniteTransitionExample() {
    val infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(
        initialValue = Color.Red,
        targetValue = Color.Green,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(color)
    )
}


@Preview
@Composable
fun AnimatableExample() {
    var ok by remember { mutableStateOf(false) }
    val color = remember { Animatable(initialValue = Color.Gray) }
    LaunchedEffect(ok) {
        color.animateTo(if (ok) Color.Green else Color.Red)
    }
    Box(
        Modifier
            .clickable {
                ok = !ok
            }
            .fillMaxSize()
            .background(color.value)
    )
}

// TODO
@Composable
fun TargetBasedAnimationExample(condition: () -> Boolean) {
    val anim = remember {
        TargetBasedAnimation(
            animationSpec = tween(2000),
            typeConverter = Float.VectorConverter,
            initialValue = 200f,
            targetValue = 1000f
        )
    }

    var playTime by remember { mutableLongStateOf(0L) }

    LaunchedEffect(anim) {
        val startTime = withFrameNanos { it }
        do {
            playTime = withFrameNanos { it } - startTime
            val animationValue = anim.getValueFromNanos(playTime)
        } while (condition())
    }
}



