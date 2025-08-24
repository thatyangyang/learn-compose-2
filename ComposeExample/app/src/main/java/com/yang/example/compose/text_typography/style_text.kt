package com.yang.example.compose.text_typography

import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yang.example.compose.R

@Composable
@Preview
fun SimpleText() {
    Text(
        stringResource(R.string.app_name),
        color = Color.Blue,
        fontSize = 18.sp,
        fontStyle = FontStyle.Italic,
        fontWeight = FontWeight.Bold
    )
}

@Composable
@Preview
fun SimpleText2() {
    val offset = Offset(5.0f, 10f)
    Text(
        stringResource(R.string.app_name),
        style = TextStyle(
            fontSize = 24.sp,
            shadow = Shadow(
                color = Color.Blue, offset = offset, blurRadius = 3f
            )
        )
    )
}

@Composable
@Preview
fun MultipleStylesInText() {
    Text(
        buildAnnotatedString {
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("H")
            }
            append("ello ")

            withStyle(
                style = SpanStyle(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("W")
            }
            append("orld")
        }
    )
}

@Composable
@Preview
fun AnnotatedHtmlStringWithLink(
    modifier: Modifier = Modifier,
    htmlText: String = """
       <h1>Jetpack Compose</h1>
       <p>
           Build <b>better apps</b> faster with <a href="https://www.android.com">Jetpack Compose</a>
       </p>
    """.trimIndent()
) {
    Text(
        AnnotatedString.fromHtml(
            htmlText,
            linkStyles = TextLinkStyles(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline,
                    fontStyle = FontStyle.Italic,
                    color = Color.Red
                )
            )
        )
    )
}

val LightBlue = Color(0xFF0066FF)
val Purple = Color(0xFF800080)

val str = """
    Do not allow people to
    dim your shine
    because they are 
    blinded, Tell them to 
    put some sunglasses
    on.
""".trimIndent()

@Composable
@Preview
fun BrushTextStyleExample() {
    val gradientColors = listOf(Color.Cyan, LightBlue, Purple)

    Text(
        text = str, style = TextStyle(
            brush = Brush.linearGradient(
                colors = gradientColors
            )
        )
    )
}

@Composable
@Preview
fun BrushSpanTextStyleExample() {
    val rainbowColors = listOf(Color.Yellow, LightBlue, Color.Blue)
    Text(
        buildAnnotatedString {
            append("Do not allow people to dim your shine\n")
            withStyle(
                SpanStyle(
                    brush = Brush.linearGradient(
                        colors = rainbowColors
                    )
                )
            ) {
                append("because they are blinded,\n")
            }
            append("Tell them to put some sunglasses on.")
        }
    )
}

@Composable
@Preview
fun BrushSpanTextStyleExample2() {
    val rainbowColors = listOf(Color.Yellow, LightBlue, Color.Blue)
    val brush = Brush.linearGradient(rainbowColors)
    Text(
        buildAnnotatedString {
            withStyle(
                SpanStyle(
                    brush = brush, alpha = .5f
                )
            ) {
                append("Text in")
            }

            withStyle(
                SpanStyle(
                    brush = brush, alpha = 1f
                )
            ) {
                append("Compose ❤\uFE0F")
            }
        }
    )
}

@Composable
@Preview
fun BasicMarqueeSample() {
    Column(Modifier.width(400.dp)) {
        Text(
            text = "Learn about why it's great to use Jetpack Compose",
            modifier = Modifier.basicMarquee(),
            fontSize = 50.sp
        )
    }
}
