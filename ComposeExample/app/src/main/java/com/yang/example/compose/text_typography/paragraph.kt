package com.yang.example.compose.text_typography

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp


@Composable
@Preview
fun CenterText() {
    Text(
        text = "Hello World",
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
@Preview
fun MultipleParagraphStylesInText() {
    Text(
        buildAnnotatedString {
            withStyle(style = ParagraphStyle(lineHeight = 30.sp)) {
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                    )
                ) {
                    append("Hello\n")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("World\n")
                }
                append("Compose")
            }
        }
    )
}


@Composable
@Preview
fun HeightPaddingSample() {
    Text(
        text = "Hello\nWorld\nCompose",
        style = LocalTextStyle.current.merge(
            TextStyle(
                lineHeight = 2.5.em,
                platformStyle = PlatformTextStyle(
                    includeFontPadding = false
                ),
                lineHeightStyle = LineHeightStyle(
                    alignment = LineHeightStyle.Alignment.Center,
                    trim = LineHeightStyle.Trim.None
                )
            )
        )
    )
}



