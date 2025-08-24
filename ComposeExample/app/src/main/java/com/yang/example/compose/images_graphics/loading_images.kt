package com.yang.example.compose.images_graphics

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.yang.example.compose.R


@Preview
@Composable
fun LoadingImagesPreview() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(R.string.hello_world)
        )

        // TODO
        AsyncImage(
            modifier = Modifier.size(100.dp),
            model = "http://gips1.baidu.com/it/u=1746086795,2510875842&fm=3028&app=3028&f=JPEG&fmt=auto?w=1024&h=1024",
            contentDescription = "Translated description of what the image contains"
        )

        Icon(
            painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
            contentDescription = stringResource(R.string.app_name)
        )
    }
}

@Preview
@Composable
fun ImageBitmapPreview() {
    val image = ImageBitmap.imageResource(R.drawable.dog)
    Column {
        Image(
            painter = painterResource(id = R.drawable.dog),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}

@Preview
@Composable
fun ImageVectorPreview() {
    val image = ImageVector.vectorResource(R.drawable.baseline_shopping_cart_24)
    Column(modifier = Modifier.padding(top = 30.dp)) {
        Image(
            painter = painterResource(id = R.drawable.baseline_shopping_cart_24),
            contentDescription = stringResource(id = R.string.app_name)
        )
    }
}