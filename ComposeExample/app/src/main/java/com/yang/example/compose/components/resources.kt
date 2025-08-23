package com.yang.example.compose.components

import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.yang.example.compose.R

val light = Font(R.font.raleway_light, FontWeight.W300)
val regular = Font(R.font.raleway_regular, FontWeight.W400)
val medium = Font(R.font.raleway_medium, FontWeight.W500)
val semibold = Font(R.font.raleway_semibold, FontWeight.W600)

val ralewayFamily = FontFamily(
    light, regular, medium, semibold
)

val ralewayTypography = Typography(
    titleLarge = TextStyle(fontFamily = ralewayFamily)
)

@Preview
@Composable
fun RalewayTheme() {
    MaterialTheme(typography = ralewayTypography) {
        ResourcesExample()
    }
}


@Preview
@Composable
fun ResourcesExample() {
    val quantity = 10
    val smallPadding = dimensionResource(R.dimen.padding_small)

    val image = AnimatedImageVector.animatedVectorResource(R.drawable.ic_hourglass_animated)
    val atEnd by remember { mutableStateOf(false) }

    Column {
        Text(text = stringResource(R.string.app_name))
        Text(text = stringResource(R.string.congratulate, "hello", 2025))

        Text(text = pluralStringResource(R.plurals.runtime_format, quantity, quantity))

        HorizontalDivider(color = colorResource(R.color.purple_200))

        Text(
            text = "Small padding is $smallPadding",
            modifier = Modifier.padding(smallPadding)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = ""
        )

        Icon(
            imageVector = Icons.Rounded.Menu,
            contentDescription = ""
        )

        Icon(
            painter = rememberAnimatedVectorPainter(image, atEnd),
            contentDescription = ""
        )

    }
}