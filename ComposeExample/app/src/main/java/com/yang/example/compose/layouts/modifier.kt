package com.yang.example.compose.layouts

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.yang.example.compose.R

@Preview
@Composable
fun GreetingPreview() {
    Greeting("Yang")
}

@Composable
fun Greeting(name: String) {
    Column(
        modifier = Modifier
            .background(Color.Cyan)
            .fillMaxWidth(fraction = 0.5F)
            .padding(34.dp)

    ) {
        Text(text = "Hello, ")
        Text(text = name)
    }
}

@Preview
@Composable
fun MatchParentSizeComposable() {
    Box {
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.LightGray)
        )

        Column(modifier = Modifier.matchParentSize()) {
//            Text(text = "fd", modifier = Modifier.matchParentSize())
            // Box 的直接 children 才可以用
        }
        ArtistCard2()
    }
}

@Preview
@Composable
fun ArtistCard2() {
    val padding = 16.dp
    Column(
        modifier = Modifier
            .clickable {

            }
            .padding(padding)
            .fillMaxWidth()
    ) {
        Row(
//            modifier = Modifier.size(width = 400.dp, height = 100.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.hero),
                contentDescription = "Artist image",
                modifier = Modifier
                    .clip(CircleShape)
//                    .size(150.dp)
                    .weight(2f)
            )

            Spacer(Modifier.width(10.dp))

            Column(modifier = Modifier.weight(1F)) {
                Text("Alfred Sisley", modifier = Modifier.paddingFromBaseline(50.dp))
                Spacer(Modifier.height(5.dp))
//                Text("3 minutes ago", modifier = Modifier.offset(x = 20.dp))
                // defer reading ASAP
                Text("3 minutes ago", modifier = Modifier.offset {
                    IntOffset.Zero.copy(x = 20, y = 0)
                })
            }
        }

        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.feathertop),
            contentDescription = "Artist image"
        )

    }
}

val reusableModifier = Modifier
    .fillMaxWidth()
    .background(Color.Red)
    .padding(12.dp)

@Composable
fun LoadingWheelAnimation() {
    val animatedState = animateFloatAsState(1.0F)

    /*Text(
        modifier = reusableModifier,
        animatedState = animatedState
    )*/
}

@Preview
@Composable
fun AuthorField() {
    Column {
        Text(
            text = "head",
            modifier = reusableModifier
        )
        Text(
            text = "content",
            modifier = reusableModifier
        )
    }
}

val reusableItemModifier = Modifier
    .padding(bottom = 12.dp)
    .size(216.dp)
    .clip(CircleShape)


@Preview
@Composable
private fun AuthorListPreview() {
    val list = listOf("a", "n", "c")
    AuthorList(list)
}

@Composable
private fun AuthorList(authors: List<String>) {
    LazyColumn {
        items(authors.size) {
            Image(
                bitmap = ImageBitmap.imageResource(R.drawable.hero),
                contentDescription = "1",
                modifier = reusableItemModifier
            )
        }
    }
}

@Preview
@Composable
fun NameListPreview() {
    Column {
        val reusableTextModifier = Modifier
//            .background(Color.Yellow)
            .padding(bottom = 12.dp)
            // Align Modifier.Element requires a ColumnScope
            .align(Alignment.CenterHorizontally)
            .weight(1f)
        Text(text = "1", modifier = reusableTextModifier.background(Color.Red))
        Text(text = "2", modifier = Modifier
//            .background(Color.Blue)
            .then(reusableTextModifier))
        /*Box {
            // weight 不生效
            Text(text = "3", modifier = reusableTextModifier)
        }*/
    }
}


