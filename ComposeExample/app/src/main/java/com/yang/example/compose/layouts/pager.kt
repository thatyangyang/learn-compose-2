package com.yang.example.compose.layouts

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.yang.example.compose.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue


val images = listOf(
    R.drawable.cupcake,
    R.drawable.donut,
    R.drawable.eclair,
    R.drawable.feathertop,
    R.drawable.froyo,
    R.drawable.sunset,
    R.drawable.rainbow,
    R.drawable.froyo,
    R.drawable.sunset,
    R.drawable.rainbow
)

val colors = listOf(
    Color.Red,
    Color.Blue,
    Color.Yellow,
    Color.Green,
    Color.Cyan,
)

private val threePageSize = object : PageSize {
    override fun Density.calculateMainAxisPageSize(
        availableSpace: Int,
        pageSpacing: Int
    ): Int {
        return (availableSpace - 2 * pageSpacing) / 3
    }
}

@Preview
@Composable
fun HorizontalPagerPreview() {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(pageCount = { 10 })

        val fling = PagerDefaults.flingBehavior(
            state = pagerState,
            pagerSnapDistance = PagerSnapDistance.atMost(10)
        )

        LaunchedEffect(pagerState) {
            snapshotFlow { pagerState.currentPage }.collect { page ->
                Log.d("HorizontalPagerPreview", "currentPage " + page)
            }
        }
        HorizontalPager(
            state = pagerState, pageSize = PageSize.Fixed(300.dp), contentPadding =
                PaddingValues(horizontal = 64.dp),
            flingBehavior = fling,
            beyondViewportPageCount = 10
        ) { page ->
            Card(
                modifier = Modifier
                    .size(300.dp)
                    .graphicsLayer {
                        val pageOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction)
                                .absoluteValue
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
            ) {
                Image(
                    painter = painterResource(images[page]),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )

                /*Text(
                    text = "Page: $page",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Red)
                        .align(Alignment.BottomEnd)
                )*/
            }
        }

        val coroutineScope = rememberCoroutineScope()
        Button(onClick = {
            coroutineScope.launch {
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }, modifier = Modifier.align(Alignment.BottomCenter)) {
            Text("Jump to Page next")
        }

        Row(
            modifier = Modifier
                .wrapContentHeight()
                .wrapContentWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
        ) {
            repeat(pagerState.pageCount) {
                val color = if (pagerState.currentPage == it) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun VerticalPagerPreview() {
    val pagerState = rememberPagerState(pageCount = { 10 })
    VerticalPager(state = pagerState) { page ->
        Text(
            text = "Page: $page",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .background(Color.Red)
                .fillMaxHeight()
                .width(200.dp)
        )
    }
}

@Preview
@Composable
fun AutoAdvancePagerPreview() {
    AutoAdvancePager(colors)
}

@Composable
fun AutoAdvancePager(pageItems: List<Color>, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        val pagerState = rememberPagerState(pageCount = { pageItems.size })
        val pagerIsDragged by pagerState.interactionSource.collectIsDraggedAsState()

        val pageInteractionSource = remember { MutableInteractionSource() }
        val pageIsPressed by pageInteractionSource.collectIsPressedAsState()

        val autoAdvance = !pagerIsDragged && !pageIsPressed

        if (autoAdvance) {
            LaunchedEffect(pagerState, pageInteractionSource) {
                while (true) {
                    delay(2000)
                    val nextPage = (pagerState.currentPage + 1) % pageItems.size
                    pagerState.animateScrollToPage(nextPage)
                }
            }
        }

        HorizontalPager(state = pagerState) { page ->
            Text(
                text = "Page: $page",
                textAlign = TextAlign.Center,
                modifier = modifier
                    .fillMaxSize()
                    .background(pageItems[page])
                    .clickable(
                        interactionSource = pageInteractionSource,
                        indication = LocalIndication.current
                    ) {

                    }
                    .wrapContentSize(align = Alignment.Center)
            )
        }

        PagerIndicator(pageItems.size, pagerState.currentPage)
    }
}

@Composable
fun PagerIndicator(pageCount: Int, currentPageIndex: Int, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pageCount) { iteration ->
                val color = if (currentPageIndex == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
    }
}

// TODO 4 -> 0 how to enter from right
