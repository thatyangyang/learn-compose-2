package com.yang.example.compose.layouts

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Preview
@Composable
fun ConstraintLayoutContent() {
    ConstraintLayout {
        val (button, text) = createRefs()

        Button(
            onClick = {},
            modifier = Modifier.constrainAs(button) {
                top.linkTo(parent.top, margin = 16.dp)
            }
        ) {
            Text("Button")
        }

        Text("text", Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 60.dp)
        })
    }
}

/*@Composable
fun DecoupledConstraintLayout() {
    BoxWithConstraints {
        val constraints = if (minWidth < 600.dp) {
            decoupledConstraints(margin = 16.dp)
        } else {
            decoupledConstraints(margin = 32.dp)
        }

        ConstraintLayout(constraintSet = constraints) {
            Button(
                onClick = {},
                modifier = Modifier.layoutId("button")
            ) {
                Text("Button")
            }

            Text("Text", Modifier.layoutId("text"))
        }
    }
}*/

private fun decoupledConstraints(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")

        constrain(button) {
            top.linkTo(parent.top, margin = margin)
        }

        constrain(text) {
            top.linkTo(button.bottom, margin = margin)
        }
    }
}

//@Preview
//@Composable
//fun ConstraintExample() {
//    ConstraintLayout {
//        val startGuideline = createGuidelineFromStart(0.1f)
//        val endGuideline = createGuidelineFromEnd(0.1f)
//        val topGuideline = createGuidelineFromTop(16.dp)
//        val bottomGuideline = createGuidelineFromBottom(16.dp)
//
//        Text("text", Modifier.constrainAs(startGuideline) ){
//            top.linkTo(startGuideline.id, margin = 60.dp)
//        })
//    }
//}