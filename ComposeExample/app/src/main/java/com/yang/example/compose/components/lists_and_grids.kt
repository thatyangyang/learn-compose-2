package com.yang.example.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


data class Message(val text: String = "", val time: String = "")

val messages = List(size = 100) { index ->
    Message("yang $index", "2023-10-01 12:00:0$index")
}

@Preview
@Composable
fun MessageList(messags: List<Message> = messages) {
    Column {
        messags.forEach {
            MessageRow(it)
        }
    }
}

@Composable
fun MessageRow(message: Message) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp),
        verticalAlignment = Alignment
            .CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(text = message.text)
        Text(text = message.time)
    }
}

@Composable
@Preview
fun LazyColumnSimple() {
    LazyColumn {
        item {
            Text(text = "First item")
        }

        items(10) { index ->
            Text(text = "Item $index")
        }

        item {
            Text(text = "Last item")
        }
    }
}

@Composable
@Preview
fun LazyColumnSimple2() {
    LazyColumn {
        items(messages) { message ->
            MessageRow(message)
        }
    }
}


