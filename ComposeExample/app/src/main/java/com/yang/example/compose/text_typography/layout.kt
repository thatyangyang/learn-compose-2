package com.yang.example.compose.text_typography

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun LongText() {
    Text(
        text = "Hello World".repeat(50),
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}