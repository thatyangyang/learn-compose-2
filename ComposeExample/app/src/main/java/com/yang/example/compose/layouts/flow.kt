package com.yang.example.compose.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun ChipItem(content: String, onClick: () -> Unit = {}) {
    OutlinedButton(
        onClick = onClick, colors = ButtonColors(
            containerColor = Color.DarkGray, contentColor =
                Color.LightGray, disabledContainerColor = Color.DarkGray, disabledContentColor =
                Color.LightGray
        )
    ) {
        Text(content)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowRowExample() {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        ChipItem("Price: High to Low")
        ChipItem("Avg rating: 4+")
        ChipItem("Free breakfast")
        ChipItem("Free cancellation")
        ChipItem("$50 pn")

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowRowAxisExample() {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        val height = 30.dp
        val roundedCornerShape = RoundedCornerShape(5.dp)
        val modifier = Modifier
            .padding(4.dp)
            .height(height)
            .clip(roundedCornerShape)
        Box(
            modifier = modifier
                .width(60.dp)
                .background(Color.Yellow)
        )
        Box(
            modifier = modifier
                .width(120.dp)
                .background(Color.Blue)
        )
        Box(
            modifier = modifier
                .width(40.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = modifier
                .width(30.dp)
                .background(Color.Green)
        )
        Box(
            modifier = modifier
                .width(150.dp)
                .background(Color.Magenta)
        )
        Box(
            modifier = modifier
                .width(40.dp)
                .background(Color.Cyan)
        )
        Box(
            modifier = modifier
                .width(80.dp)
                .background(Color.LightGray)
        )
        Box(
            modifier = modifier
                .width(30.dp)
                .background(Color.Gray)
        )
        Box(
            modifier = modifier
                .width(60.dp)
                .background(Color.Red)
        )
        Box(
            modifier = modifier
                .width(50.dp)
                .background(Color.Yellow)
        )

    }
}


@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowRowIndividualAlignmentExample() {
    FlowRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.Top,
        maxItemsInEachRow = 3
    ) {
        val roundedCornerShape = RoundedCornerShape(5.dp)
        val modifier = Modifier
            .padding(4.dp)
            .clip(roundedCornerShape)
            .align(Alignment.CenterVertically)
        Box(
            modifier = modifier
                .width(60.dp)
                .height(40.dp)
                .background(Color.Yellow)
        )
        Box(
            modifier = modifier
                .width(80.dp)
                .height(140.dp)
                .background(Color.Blue)
        )
        Box(
            modifier = modifier
                .width(100.dp)
                .height(160.dp)
                .background(Color.DarkGray)
        )
        Box(
            modifier = modifier
                .width(20.dp)
                .height(100.dp)
                .background(Color.Green)
        )
        Box(
            modifier = modifier
                .width(150.dp)
                .height(60.dp)
                .background(Color.Magenta)
        )
        Box(
            modifier = modifier
                .width(40.dp)
                .height(40.dp)
                .background(Color.Cyan)
        )
        Box(
            modifier = modifier
                .width(80.dp)
                .height(10.dp)
                .background(Color.LightGray)
        )
        Box(
            modifier = modifier
                .width(20.dp)
                .height(60.dp)
                .background(Color.Gray)
        )
        Box(
            modifier = modifier
                .width(60.dp)
                .height(40.dp)
                .background(Color.Red)
        )
        Box(
            modifier = modifier
                .width(120.dp)
                .height(20.dp)
                .background(Color.Yellow)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowWightPreview() {
    val rows = 3
    val columns = 3
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = rows
    ) {
        val itemModifier = Modifier
            .padding(4.dp)
            .height(80.dp)
            .weight(1f)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Blue)
        repeat(rows * columns) {
            Spacer(modifier = itemModifier)
        }
        Spacer(modifier = itemModifier)
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowWightPreview2() {
    val rows = 3
    val columns = 3
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = 2
    ) {
        val itemModifier = Modifier
            .padding(4.dp)
            .height(80.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Blue)
        repeat(rows * columns) { item ->
            if ((item + 1) % 3 == 0) {
                Spacer(modifier = itemModifier.fillMaxWidth())
            } else {
                Spacer(modifier = itemModifier.weight(0.5f))
            }

        }
        Spacer(modifier = itemModifier)
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowFractionPreview() {
    val rows = 3
    val columns = 3
    FlowRow(
        modifier = Modifier.padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxItemsInEachRow = 3
    ) {
        val itemModifier = Modifier
            .height(200.dp)
            .clip(RoundedCornerShape(8.dp))
        Spacer(
            modifier = itemModifier
                .width(60.dp)
                .background(Color.Cyan)
        )
        Spacer(
            modifier = itemModifier
//                .fillMaxWidth(0.7f) // fraction of maxWidth
                .weight(0.7f) // fraction of remaining
                .background(Color.Yellow)
        )
        Spacer(
            modifier = itemModifier
                .weight(1f) // remaining
                .background(Color.Magenta)
        )
    }
}

val texts = listOf(
    "fdasfds",
    "ffda",
    "erer",
    "lfl",
    "fdasfds",
    "ffda",
    "erfdfdefdfd\nsfdsfadfsr",
    "lfl",
    "fdasfds",
    "ffdfdafda",
    "erfdfder",
    "lffdafl",
    "erefdafr",
    "lfl",
)

@OptIn(ExperimentalLayoutApi::class)
@Preview
@Composable
fun FlowMaxPreview() {
    FlowColumn(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        maxItemsInEachColumn = 5
    ) {
        repeat(texts.size) {
            Box(
                Modifier
                    .fillMaxColumnWidth()
                    .border(1.dp, Color.DarkGray, RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Text(text = texts[it], fontSize = 18.sp, modifier = Modifier.padding(3.dp))
            }
        }
    }
}

