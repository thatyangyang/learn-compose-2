package com.yang.example.compose.touch_input

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.yang.example.compose.R
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
    var contextMenuPhotoId by rememberSaveable { mutableStateOf<Int?>(null) }
    // Implementation here
    LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp)) {
        items(photos, key = { it.id }) { photo ->
            ImageItem(
                photo = photo,
                modifier = Modifier.combinedClickable(
                    onLongClick = {
                        contextMenuPhotoId = photo.id
                    },
                    onClick = {
                        activePhotoId = photo.id
                    })
            )
        }
    }
    if (activePhotoId != null) {
        FullScreenImage(
            photo = photos.first { it.id == activePhotoId },
            onDismiss = { activePhotoId = null }
        )
    }
    if (contextMenuPhotoId != null) {
        PhotoActionsSheet(
            onDismissRequest = { contextMenuPhotoId = null }
        )
    }
}

@Composable
private fun FullScreenImage(photo: Photo, onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Scrim(onDismiss, Modifier.fillMaxSize())
        /*Image(
            painter = rememberAsyncImagePainter(model = photo.highResUrl),
            contentDescription = null,
            modifier = Modifier.clickable {
                // onDismiss()
            }
        )*/
        ImageWithZoom(photo)
    }
}

@Composable
private fun ImageWithZoom(photo: Photo, modifier: Modifier = Modifier) {
    var zoomed by remember { mutableStateOf(false) }
    var zoomOffset by remember { mutableStateOf(Offset.Zero) }
    Image(
        painter = rememberAsyncImagePainter(model = photo.highResUrl),
        contentDescription = null,
        modifier = modifier
            .aspectRatio(1f)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = { offset ->
                        zoomOffset = if (zoomed) Offset.Zero else calculateOffset(offset, size)
                        zoomed = !zoomed
                    }
                )
            }
            .graphicsLayer {
                scaleX = if (zoomed) 2f else 1f
                scaleY = if (zoomed) 2f else 1f
                translationX = zoomOffset.x
                translationY = zoomOffset.y
            }
    )
}

private fun calculateOffset(tapOffset: Offset, size: IntSize): Offset {
    val offsetX =
        (-((tapOffset.x - (size.width / 2f))) * 2f).coerceIn(-size.width / 2f, size.width / 2f)
    return Offset(offsetX, 0f)
}

@Composable
private fun Scrim(onDismiss: () -> Unit, modifier: Modifier = Modifier) {
    val strClose = stringResource(R.string.close)
    Box(
        modifier
            .pointerInput(onDismiss) {
                detectTapGestures {
                    onDismiss()
                }
                // 需要 invoke  (), 因为onTap的函数类型(参数)与传入的 onDismiss()不同
//                detectTapGestures(onTap = { onDismiss })
            }
            .semantics(mergeDescendants = true) {
                contentDescription = strClose
                onClick {
                    onDismiss()
                    true
                }
            }
            .onKeyEvent {
                if (it.key == Key.Escape) {
                    onDismiss()
                    true
                } else {
                    false
                }
            }
            .background(Color.DarkGray.copy(alpha = 0.75f))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PhotoActionsSheet(onDismissRequest: () -> Unit) {
    ModalBottomSheet(
        onDismissRequest = onDismissRequest
    ) {
        ListItem(
            headlineContent = { Text("Add to album") },
            leadingContent = { Icon(Icons.Default.Add, contentDescription = null) }
        )
        ListItem(
            headlineContent = { Text("Add to favorites") },
            leadingContent = { Icon(Icons.Default.FavoriteBorder, null) }
        )
        ListItem(
            headlineContent = { Text("Share") },
            leadingContent = { Icon(Icons.Default.Share, null) }
        )
        ListItem(
            headlineContent = { Text("Remove") },
            leadingContent = { Icon(Icons.Default.DeleteOutline, null) }
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