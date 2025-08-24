package com.yang.example.compose.text_typography

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// TODO Value-based to State-based

@Composable
@Preview
fun TextFieldSample() {
    var text by remember { mutableStateOf("Hello") }
    Column {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") }
        )
    }
}

@Composable
@Preview
fun SimpleOutlinedTextFieldSample() {
    var text by remember { mutableStateOf("Hello") }
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") }
        )
    }
}


@Composable
@Preview
fun StyledTextField() {
    var text by remember { mutableStateOf("Hello\nWorld\nInvisible") }
    Column(modifier = Modifier.padding(top = 20.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
            maxLines = 2,
            textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
@Preview
fun BrushStyledTextField() {
    var text by remember { mutableStateOf("") }
    val rainbowColors = listOf(Color.Yellow, LightBlue, Color.Blue)
    val brush = remember {
        Brush.linearGradient(
            colors = rainbowColors
        )
    }
    Column(modifier = Modifier.padding(top = 20.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Label") },
            maxLines = 2,
            textStyle = TextStyle(brush = brush),
            modifier = Modifier.padding(20.dp)
        )
    }
}

@Composable
@Preview
fun PasswordTextField() {
    var password by remember { mutableStateOf("") }

    TextField(
        value = password,
        onValueChange = { password = it },
        label = { Text("Enter password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
    )
}

@Composable
@Preview
fun NoLeadingZeroesTextField() {
    var input by remember { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = { newText ->
            input = newText.trimStart { it == '0' }
        },
        label = { Text("Enter password") },
    )
}

