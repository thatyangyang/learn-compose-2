package com.yang.example.compose.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.RichTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun PlainTooltipExample(
    modifier: Modifier = Modifier,
    plainTooltipText: String = "Add to favorites"
) {
    TooltipBox(
        modifier = modifier.fillMaxSize(),
        positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
        tooltip = {
            PlainTooltip { Text(plainTooltipText) }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Add to favorites"
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun RichTooltipExample(
    modifier: Modifier = Modifier,
    richTooltipSubheadText: String = "Rich Tooltip",
    richTooltipText: String = "Rich tooltips support multiple lines of informational text."
) {
    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(title = { Text(richTooltipSubheadText) }) {
                Text(richTooltipText)
            }
        },
        state = rememberTooltipState()
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "Show more information"
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AdvancedRichTooltipExample(
    modifier: Modifier = Modifier,
    richTooltipSubheadText: String = "Custom Rich Tooltip",
    richTooltipText: String = "Rich tooltips support multiple lines of informational text.",
    richTooltipActionText: String = "Dismiss"
) {
    val tooltipState = rememberTooltipState()
    val coroutineScope = rememberCoroutineScope()

    TooltipBox(
        modifier = modifier,
        positionProvider = TooltipDefaults.rememberRichTooltipPositionProvider(),
        tooltip = {
            RichTooltip(
                title = { Text(richTooltipSubheadText) },
                action = {
                    Row {
                        TextButton(onClick = {
                            coroutineScope.launch {
                                tooltipState.dismiss()
                            }
                        }) {
                            Text(richTooltipActionText)
                        }
                    }
                },
                caretSize = DpSize(32.dp, 16.dp)
            ) {
                Text(richTooltipText)
            }
        },
        state = tooltipState
    ) {
        IconButton(onClick = {
            coroutineScope.launch {
                tooltipState.show()
            }
        }) {
            Icon(
                imageVector = Icons.Filled.Call,
                contentDescription = "Open camera"
            )
        }
    }
}