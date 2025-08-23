package com.yang.example.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun DividerExample() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("First Item")
        HorizontalDivider(thickness = 2.dp, color = Color.Red)
        Text("Second Item")
    }

}

@Preview
@Composable
fun VerticalDividerExample() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("First Item")
        VerticalDivider(thickness = 5.dp, color = Color.Blue)
        Text("Second Item")
    }
}