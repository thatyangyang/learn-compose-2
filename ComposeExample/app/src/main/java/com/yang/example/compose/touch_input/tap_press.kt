package com.yang.example.compose.touch_input

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.yang.example.compose.util.rememberRandomSampleImageUrl

private class Photo(
    val id: Int,
    val url: String,
    val highResUrl: String
)


@Composable
@Preview
private fun TapPreview() {
    val photos = List(100) {
        val url = rememberRandomSampleImageUrl(width = 256)
        Photo(it, url, url.replace("256", "1024"))
    }
    Surface {
        ImageGrid(photos)
    }
}

@Composable
private fun ImageGrid(photos: List<Photo>) {
    var activePhotoId by remember { mutableStateOf<Int?>(null) }
    // Implementation here
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(photos, key = { it.id }) { photo ->
            ImageItem(
                photo = photo,
                modifier = Modifier.clickable {
                    activePhotoId = photo.id
                }
            )
        }
    }
    if (activePhotoId != null) {
        FullScreenImage(
            photo = photos.first { it.id == activePhotoId },
            onDismiss = { activePhotoId = null }
        )
    }
}

@Composable
private fun FullScreenImage(photo: Photo, onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(Color.Red),
        contentAlignment = Alignment.Center
    ) {
//        Scrim(onDismiss, Modifier.fillMaxSize())
        Image(
            painter = rememberAsyncImagePainter(model = photo.highResUrl),
            contentDescription = null,
            modifier = Modifier.clickable {
                onDismiss()
            }
        )
    }
}

@Composable
private fun ImageItem(photo: Photo, modifier: Modifier) {
    Image(
        painter = rememberAsyncImagePainter(model = photo.url),
        contentDescription = null,
        modifier = modifier.aspectRatio(1f)
    )
}