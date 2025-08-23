package com.yang.example.compose.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.example.compose.R
import kotlinx.coroutines.delay


@Preview
@Composable
fun ButtonsExample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(onClick = {}) {
            Text("Filled")
        }
        FilledTonalButton(onClick = {}) {
            Text("FilledTonalButton")
        }
        OutlinedButton(onClick = {}) {
            Text("OutlinedButton")
        }
        ElevatedButton(onClick = {}) {
            Text("ElevatedButton")
        }
        TextButton(onClick = {}) {
            Text("TextButton")
        }
    }
}

@Preview
@Composable
fun FABExample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FloatingActionButton(onClick = {}) {
            Icon(Icons.Filled.Add, contentDescription = "Localized description")
        }

        SmallFloatingActionButton(
            onClick = {},
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Small Localized description")
        }

        LargeFloatingActionButton(
            onClick = {},
            shape = CircleShape
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Large Localized description")
        }

        ExtendedFloatingActionButton(
            onClick = {},
            icon = { Icon(Icons.Filled.Edit, "Extended Localized description") },
            text = { Text(text = "Extended FAB") }
        )
    }
}

@Preview
@Composable
fun ToggleIconButtonExample() {
    var isToggled by rememberSaveable { mutableStateOf(false) }

    IconButton(onClick = { isToggled = !isToggled }) {
        Icon(
            painter = if (isToggled) painterResource(R.drawable.favorite_filled) else
                painterResource(R.drawable.favorite),
            contentDescription = if (isToggled) "Favorite" else "Unfavorite",
        )
    }
}

@Preview
@Composable
fun MomentaryIconButtonPreview() {
    var pressedCount by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MomentaryIconButton(
            unselectedImage = R.drawable.fast_rewind,
            selectedImage = R.drawable.fast_rewind_filled,
            stepDelay = 100L,
            contentDescription = "Recrease count button",
            onClick = { pressedCount-- }
        )

        Spacer(modifier = Modifier)
        Text("advance by $pressedCount frames")
        Spacer(modifier = Modifier)

        MomentaryIconButton(
            unselectedImage = R.drawable.fast_forward,
            selectedImage = R.drawable.fast_forward_filled,
            stepDelay = 100L,
            contentDescription = "Increase count button",
            onClick = { pressedCount++ }
        )
    }
}

@Composable
fun MomentaryIconButton(
    unselectedImage: Int,
    selectedImage: Int,
    contentDescription: String,
    modifier: Modifier = Modifier,
    stepDelay: Long = 100L,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    // what's the meaning of remembering this onClick
    val pressedListener by rememberUpdatedState(onClick)
    val listener by remember { mutableStateOf(onClick) }

    LaunchedEffect(isPressed) {
        while (isPressed) {
            delay(stepDelay.coerceIn(1L, Long.MAX_VALUE))
//            pressedListener()
            onClick()
        }
    }

    IconButton(
        onClick = onClick,
        modifier = modifier,
        interactionSource = interactionSource
    ) {
        Icon(
            painter = if (isPressed) painterResource(selectedImage) else
                painterResource(unselectedImage),
            contentDescription = contentDescription
        )
    }
}

@Composable
@Preview
fun SingleChoiceSegmentedButton(modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf("Day", "Month", "Year")
    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, option ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                onClick = { selectedIndex = index },
                selected = index == selectedIndex,
                label = { Text(option) }
            )
        }
    }
}

@Composable
@Preview
fun MultiChoiceSegmentedButton() {
    val selectedOptions = remember { mutableStateListOf(false, false, false) }
    val options = listOf("Walk", "Ride", "Drive")

    MultiChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = options.size
                ),
                checked = selectedOptions[index],
                onCheckedChange = {
                    selectedOptions[index] = it
                },
                icon = { SegmentedButtonDefaults.Icon(active = selectedOptions[index]) },
                label = {
                    when (label) {
                        "Walk" -> Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Directions Walk"
                        )

                        "Ride" -> Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Directions Walk"
                        )

                        "Drive" -> Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Directions Walk"
                        )
                    }
                }
            )

        }
    }
}

