package com.yang.example.compose.animation

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.yang.example.compose.R


@Preview
@Composable
fun AnimatedVisibilityExample2() {
    Box(modifier = Modifier.fillMaxSize()) {
        var visible by remember { mutableStateOf(false) }
        val density = LocalDensity.current
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically {
                with(density) {
                    -40.dp.roundToPx()
                }
            } + expandVertically(
                expandFrom = Alignment.Top
            ) + fadeIn(initialAlpha = 0.3f),
            exit = slideOutVertically()
                    + shrinkVertically()
                    + fadeOut()

        ) {
            Text(
                text = "Hello World", color = Color.Red,
                modifier = Modifier
                    .fillMaxSize()
                    .height(20.dp)
            )
        }

        Button(
            onClick = { visible = !visible },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("show/hide")
            Log.d("AnimatedVisibility", "button")
        }
    }

}

@Preview
@Composable
fun MutableTransitionStateExample() {
    val state = remember {
        MutableTransitionState(false).apply {
            targetState = true
        }
    }
    Column {
        AnimatedVisibility(visibleState = state) {
            Text(text = "Hello, world!")
        }

        Text(
            text = when {
                state.isIdle && state.currentState -> "Visible"
                !state.isIdle && state.currentState -> "Disappearing"
                state.isIdle && !state.currentState -> "Invisible"
                else -> "Appearing"
            }
        )
    }
}

@Preview
@Composable
fun AnimatedContentExample1() {
    Row {
        var count by remember { mutableIntStateOf(0) }
        Button(onClick = { count++ }) {
            Text("Add")
        }
        AnimatedContent(
            targetState = count,
            label = "animated content"
        ) { targetCount ->
            // Make sure to use `targetCount`, not `count`.
            Text(text = "Count: $targetCount")
        }
    }
}

@Preview
@Composable
fun AnimatedContentExample2() {
    Row {
        var count by remember { mutableIntStateOf(0) }
        Button(onClick = { count++ }) {
            Text("Add")
        }
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                if (targetState > initialState) {
                    slideInVertically { height -> height } + fadeIn() togetherWith
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    slideInVertically { height -> -height } + fadeIn() togetherWith
                            slideOutVertically { height -> height } + fadeOut()
                }.using(
                    // Disable clipping since the faded slide-in/out should
                    // be displayed out of bounds.
                    SizeTransform(clip = false)
                )
            }, label = "animated content"
        ) { targetCount ->
            Text(text = "$targetCount")
        }
    }
}

@Preview
@Composable
fun AnimatedContentExample3() {
    Row {
        var expanded by remember { mutableStateOf(false) }
        Surface(
            color = MaterialTheme.colorScheme.primary,
            onClick = { expanded = !expanded }
        ) {
            AnimatedContent(
                targetState = expanded,
                transitionSpec = {
                    fadeIn(animationSpec = tween(150, 150)) togetherWith
                            fadeOut(animationSpec = tween(150)) using
                            SizeTransform { initialSize, targetSize ->
                                if (targetState) {
                                    keyframes {
                                        // Expand horizontally first.
                                        IntSize(targetSize.width, initialSize.height) at 150
                                        durationMillis = 300
                                    }
                                } else {
                                    keyframes {
                                        // Shrink vertically first.
                                        IntSize(initialSize.width, targetSize.height) at 150
                                        durationMillis = 300
                                    }
                                }
                            }
                }, label = "size transform"
            ) { targetExpanded ->
                if (targetExpanded) {
                    Text(
                        text =
                            """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button times.
                """, modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .background(Color.Green)
                    )
                } else {
                    Icon(
                        painter = painterResource(R.drawable.ic_logo),
                        contentDescription = "",
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.Green)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CrossfadeExample() {
    var currentPage by remember { mutableStateOf("A") }
    Crossfade(
        targetState = currentPage,
        label = "cross fade",
        modifier = Modifier
            .size(200.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                currentPage = if (currentPage != "A") "A" else "B"
            }
    ) { screen ->
        when (screen) {
            "A" -> Text(
                "Page A", modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Red)
            )

            "B" -> Text(
                "Page B", modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Blue)
            )
        }
    }
}

@Preview
@Composable
fun ContentSize() {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .background(Color.Green)
            .animateContentSize()
            .height(if (expanded) 400.dp else 200.dp)
            .width(if (expanded) 600.dp else 200.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                expanded = !expanded
            }

    ) {
    }
}



