package com.yang.example.compose.animation

import android.util.Log
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.example.compose.R

@Preview
@Composable
fun AnimatedVisibilityCookbook() {
    Box(modifier = Modifier.fillMaxSize()) {
        var visible by remember { mutableStateOf(true) }

        AnimatedVisibility(visible) {
            Box(
                modifier = Modifier
                    .size(20.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.Green)
            )
            Log.d("AnimatedVisibility", "visible: $visible")
        }

        Log.d("AnimatedVisibility", "middle")

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
fun AnimatedVisibilityCookbook_ModifierAlpha() {
    Box(modifier = Modifier.fillMaxSize()) {
        var visible by remember { mutableStateOf(true) }
        val animatedAlpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            label = "alpha",
        )

        Box(
            modifier = Modifier
                .size(20.dp)
                .graphicsLayer {
                    alpha = animatedAlpha
                }
                .clip(RoundedCornerShape(8.dp))
                .background(Color.Green)
                .align(Alignment.TopCenter)
        )

        Button(
            onClick = { visible = !visible },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("show/hide")
        }
    }
}


@Preview
@Composable
private fun AnimatedContentSimple() {
    // [START android_compose_animations_animated_content_simple]
    Row {
        var count by remember { mutableIntStateOf(0) }
        Button(onClick = { count = if (count == 0) 1 else 0 }) {
            Text("Add")
        }
        AnimatedContent(
            targetState = count,
            label = "animated content"
        ) { targetCount ->
            // Make sure to use `targetCount`, not `count`.
            if (targetCount == 0) {
                Text(text = "Count: $targetCount")
            } else {
                Image(painter = painterResource(R.drawable.ic_logo), contentDescription = "")
            }
            Log.d("Count", "Count: $count targetCount $targetCount")
        }
    }
    // [END android_compose_animations_animated_content_simple]
}

@Preview
@Composable
private fun CrossfadeSimple() {
    Box(modifier = Modifier.fillMaxSize()) {
        var currentPage by remember { mutableStateOf("A") }
        Crossfade(targetState = currentPage, label = "cross fade") { screen ->
            when (screen) {
                "A" -> Text("Page A A")
                "B" -> Text("B Page B")
            }
        }


        Button(
            onClick = { currentPage = if (currentPage == "A") "B" else "A" },
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            Text("show/hide")
            Log.d("AnimatedVisibility", "button")
        }
    }


}