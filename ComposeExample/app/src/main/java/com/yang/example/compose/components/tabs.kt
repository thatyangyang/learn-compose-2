package com.yang.example.compose.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

enum class Destination(
    val route: String,
    val label: String,
    val icon: ImageVector,
    val contentDescription: String
) {
    SONGS("songs", "Songs", Icons.Default.Menu, "Songs"),
    ALBUM("album", "Album", Icons.Default.Add, "Album"),
    PLAYLISTS("playlist", "Playlist", Icons.Default.PlayArrow, "Playlist")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun NavigationTabExample() {
    val navController = rememberNavController()
    val startDestination = Destination.SONGS
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(Modifier) { contentPadding ->
        PrimaryTabRow(
            selectedTabIndex = selectedDestination,
            modifier = Modifier.padding(contentPadding)
        ) {
            Destination.entries.forEachIndexed { index, destination ->
                Tab(
                    selected = selectedDestination == index,
                    onClick = {
                        navController.navigate(route = destination.route)
                        selectedDestination = index
                    },
                    text = {
                        Text(
                            text = destination.label,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                )
            }
        }
        AppNavHost(navController, startDestination)
    }
}

@Composable
fun AppNavHost(
    navHostController: NavHostController, startDestination: Destination,
    modifier: Modifier = Modifier
) {
    NavHost(
        navHostController,
        startDestination = startDestination.route
    ) {
        Destination.entries.forEach { destination ->
            composable(destination.route) {
                when (destination) {
                    Destination.SONGS -> SongsScreen()
                    Destination.ALBUM -> AlbumScreen()
                    Destination.PLAYLISTS -> PlaylistScreen()
                }
            }
        }
    }
}


@Composable
fun SongsScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Songs Screen")
    }
}

@Composable
fun AlbumScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Album Screen")
    }
}

@Composable
fun PlaylistScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Playlist Screen")
    }
}