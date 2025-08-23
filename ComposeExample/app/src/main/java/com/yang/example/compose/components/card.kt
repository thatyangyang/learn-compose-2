package com.yang.example.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
@Preview
fun CardMinimalExample() {
    Card {
        Text(text = "hello world")
    }
}


@Preview
@Composable
fun FilledCardExample() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 60.dp
        ),
        modifier = Modifier.size(width = 240.dp, height = 100.dp)
    ) {
        Text(
            text = "Filled hello world",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ElevationCardExample() {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 60.dp
        ),
        modifier = Modifier.size(width = 240.dp, height = 100.dp)
    ) {
        Text(
            text = "Filled hello world",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun OutlinedCardExample() {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        border = BorderStroke(3.dp, Color.Black),
        modifier = Modifier.size(width = 240.dp, height = 100.dp)
    ) {
        Text(
            text = "Filled hello world",
            modifier = Modifier.padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}


