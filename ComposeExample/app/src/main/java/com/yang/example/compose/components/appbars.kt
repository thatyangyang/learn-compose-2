package com.yang.example.compose.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun SmallTopAppBarExample() {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                title = {
                    Text("Small Top App Bar")
                }
            )
        }
    ) {
        ScrollContent(it)
    }
}

@Composable
fun ScrollContent(innerPadding: PaddingValues) {
    val range = 1..100
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = innerPadding,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(range.count()) { index ->
            Text(text = "- List item number: $index")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun CenterAlignedTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                title = {
                    Text(
                        "Centered Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MediumTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                title = {
                    Text(
                        "Medium Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun LargeTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            LargeTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                title = {
                    Text(
                        "Large Top App Bar",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        }
    ) { innerPadding ->
        ScrollContent(innerPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun BottomAppBarExample() {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                actions = {
                    IconButton({}) {
                        Icon(Icons.Filled.Check, contentDescription = "Localized description")
                    }
                    IconButton({}) {
                        Icon(Icons.Filled.Edit, contentDescription = "Localized description")
                    }
                    IconButton({}) {
                        Icon(Icons.Filled.MailOutline, contentDescription = "Localized description")
                    }
                    IconButton({}) {
                        Icon(Icons.Filled.Phone, contentDescription = "Localized description")
                    }
                },
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {},
                        containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation()
                    ) {
                        Icon(Icons.Filled.Add, "Localized description")
                    }
                }
            )
        }
    ) { innerPadding ->
        Text(text = "Bottom App Bar Example", modifier = Modifier.padding(innerPadding))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBarNavigationExamplePreview() {
    TopBarNavigationExample {
        Log.d("TopBarNavigation", "Navigate back ${Thread.currentThread()}") // main thread
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNavigationExample(navigateBack: () -> Unit) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Navigation Back Example")
                },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, "Back")
                    }
                }
            )
        }
    ) {
        Text("Click the back button to pop from the back stack", modifier = Modifier.padding(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppBarSelectionActionsPreview() {
    var selectedItems by remember { mutableStateOf(setOf<Int>()) }
    // 数据应该离使用的地方近
    AppBarSelectionActions(selectedItems) {
        // + - 操作产生新的集合，需要赋值，才能更新
        selectedItems = if (selectedItems.isEmpty()) {
            selectedItems + 1
        } else {
            selectedItems - 1
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarSelectionActions(
    selectedItems: Set<Int>, modifier: Modifier = Modifier,
    onClickAction: () -> Unit = {}
) {
    val hasSelection = selectedItems.isNotEmpty()
    val topBarText = if (hasSelection) {
        "Selected ${selectedItems.size} items"
    } else {
        "List of items"
    }

    TopAppBar(
        title = {
            Text(topBarText)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary
        ),
        actions = {
            IconButton(onClick = onClickAction) {
                Icon(Icons.Filled.Share, contentDescription = "Share items")
            }
        }
    )
}

@Preview
@Composable
fun AppBarMultiSelectionExamplePreview() {
    AppBarMultiSelectionExample()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AppBarMultiSelectionExample(modifier: Modifier = Modifier) {
    val listItems by remember { mutableStateOf(listOf(11, 22, 33, 44, 55, 66, 77, 88, 99)) }
    var selectedItems by remember { mutableStateOf(setOf<Int>()) }
    Scaffold(
        topBar = {
            AppBarSelectionActions(selectedItems)
        }
    ) { innerPadding ->
        LazyColumn(contentPadding = innerPadding) {
            itemsIndexed(listItems) { _, item ->
                val isSelected = selectedItems.contains(item)
                ListItemSelectable(
                    isSelected, Modifier.combinedClickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {},
                        onLongClick = {
                            println(item)
                            if (isSelected) selectedItems -= item else selectedItems += item
                        }
                    ))
            }
        }
    }
}

@Composable
fun ListItemSelectable(isSelected: Boolean, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        ListItem(
            headlineContent = { Text("Long Press to select item or deselect item") },
            leadingContent = {
                if (isSelected) {
                    Icon(Icons.Filled.Check, contentDescription = "Selected")
                }
            }
        )
    }
}
