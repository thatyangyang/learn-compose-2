package com.yang.example.compose.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import com.yang.example.compose.R


@Preview
@Composable
fun ArtistCard() {
    Text("Alfred Sisley")
    Text("3 minutes ago")
}

@Preview
@Composable
fun ArtistCardColumn() {
    Column {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

@Preview
@Composable
fun ArtistCardRow() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("Alfred Sisley")
        Text("3 minutes ago")
    }
}

class Artist {
    var image: ImageBitmap = ImageBitmap(100, 100)
    val name = "yang"
    val lastSeenOnline = "1 minite ago"
}

@Preview
@Composable
fun ShowArtistAvatar() {
    val artist = Artist().apply {
        image = ImageBitmap.imageResource(R.drawable.avatar_rengwuxian)
    }
//    ArtistAvatar(artist)
    ArtistAvatarRow(artist)
}

@Composable
fun ArtistAvatar(artist: Artist) {
    Box {
        Image(bitmap = artist.image, contentDescription = "Artist image")
        Icon(
            Icons.Filled.Check, modifier = Modifier.align(Alignment.BottomEnd), contentDescription =
                "Artist " +
                        "icon"
        )
    }
}

@Composable
fun ArtistAvatarRow(artist: Artist) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Image(bitmap = artist.image, contentDescription = "Artist image")
        Column {
            Text("Alfred Sisley")
            Text("3 minutes ago")
        }
    }
}

@Preview
@Composable
fun WithConstraintsComposable() {
    BoxWithConstraints {
        Text("My minHeight is ${this.minHeight} while my maxWidth is $maxWidth")
    }
}

@Preview
@Composable
fun HomeScreen() {
    ModalNavigationDrawer(drawerContent = {
        Box(
            contentAlignment = Alignment.Center, modifier =
                Modifier.background(color = Color.Red)
        ) {
            Text("drawerContent")
        }
    }, modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = { Text("topBar") }
        ) { paddingValues ->
            Box(
                contentAlignment = Alignment.Center, modifier =
                    Modifier.background(color = Color.Blue)
            ) {
                Text("${paddingValues.calculateBottomPadding()}")
            }
        }
    }
}
