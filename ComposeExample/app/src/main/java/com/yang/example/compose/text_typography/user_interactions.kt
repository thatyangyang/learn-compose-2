package com.yang.example.compose.text_typography

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@Preview
fun SelectableText() {
    Box(modifier = Modifier.padding(top = 100.dp)) {
        SelectionContainer {
            Column {
                Text(
                    "You can select this text",
                    fontSize = 30.sp
                )
                Text(
                    "You can select this text",
                    fontSize = 30.sp
                )
                DisableSelection {
                    Text(
                        "You can not select this text",
                        fontSize = 30.sp
                    )
                }
                Text(
                    "You can select this text",
                    fontSize = 30.sp
                )
                Text(
                    "You can select this text",
                    fontSize = 30.sp
                )
            }
        }
    }
}

@Composable
@Preview
fun AnnotatedStringWithLinkSample() {
    Text(
        buildAnnotatedString {
            append("Click ")
            withLink(
                LinkAnnotation.Url(
                    url = "https://developer.android.com",
                    styles = TextLinkStyles(style = SpanStyle(color = Color.Red))
                )
            ) {
                append("Developers")
            }
            append("Click ")
            withLink(
                LinkAnnotation.Url(
                    url = "https://developer.android.com/jetpack/compose",
                    styles = TextLinkStyles(style = SpanStyle(color = Color.Green))
                )
            ) {
                append("Developers")
            }
        },
        fontSize = 30.sp,
        modifier = Modifier.padding(top = 100.dp)
    )
}

@Composable
@Preview
fun AnnotatedStringWithListenerSample() {
    val uriHandler = LocalUriHandler.current
    Text(
        buildAnnotatedString {
            append("Build better apps faster with ")
            val link =
                LinkAnnotation.Url(
                    "https://developer.android.com/jetpack/compose",
                    TextLinkStyles(SpanStyle(color = Color.Blue))
                ) {
                    val url = (it as LinkAnnotation.Url).url
                    // log some metrics
                    uriHandler.openUri(url)
                }
            withLink(link) { append("Jetpack Compose") }
        }
    )
}