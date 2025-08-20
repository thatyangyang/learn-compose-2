package com.yang.example.compose.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yang.example.compose.R

@Preview
@Composable
fun ConstraintsDemoPreview() {
    Image(
        painter = painterResource(R.drawable.hero),
        contentDescription = "",
//        Modifier
//            .fillMaxWidth()
//            .wrapContentSize()
//            .size(50.dp)
        // actual size of frame, take a reverse way
        // 100 + 10*2, and then clip
//        Modifier
//            .clip(CircleShape)
//            .padding(10.dp)
//            .size(100.dp)

        // measure (child->parent), layout
        // clip does not change the constraints
        Modifier
            .padding(10.dp)
            .size(100.dp)
            .clip(CircleShape)
    )
}