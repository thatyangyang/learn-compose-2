package com.yang.example.compose.touch_input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventPass
import androidx.compose.ui.input.pointer.PointerInputFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview
@Composable
fun GesturesComponentExample() {
    Column {
        Button(onClick = {  // material3
            println("button onClick")
        }) { Text(text = "click") }

        Box(modifier = Modifier.clickable { // foundation
            println("box clickable onClick")
        }) {
            Text(text = "click")
        }
    }
}

@Preview
@Composable
fun LogPointerEvents(filter: PointerInputFilter? = null) {
    var log by remember { mutableStateOf("") }
    Column {
        Text(log)
        Box(
            Modifier
                .size(200.dp)
                .background(Color.Green)
                .pointerInput(filter) {
                    awaitPointerEventScope { // low level, ui
                        while (true) {
                            val event = awaitPointerEvent()
                            if (filter == null) {
                                log = "${event.type}, ${event.changes.first().position}"
                            }
                        }
                    }
                }
        )
    }
}

@Preview
@Composable
fun LogPointerEventsFullGestures() {
    var log by remember { mutableStateOf("") }
    Column {
        Text(log)
        Box(
            Modifier
                .size(200.dp)
                .background(Color.Green)
                .pointerInput(Unit) {
                    // suspend, so detectDragGestures can not be reached
                    detectTapGestures {
                        log = "tap + ${System.currentTimeMillis()}"
                    }       // medium level, foundation

                    detectDragGestures { _, _ -> log = "drag" } // medium level, foundation
                }
                .pointerInput(Unit) {
                    detectDragGestures { _, _ -> log = "drag + ${System.currentTimeMillis()}" }
                }
                .pointerInput(Unit) {
                    awaitEachGesture {

                    }
                }
        )
    }
}

@Preview
@Composable
fun LogPointerEventsSimpleClickable() {
    var log by remember { mutableStateOf("") }
    Column {
        Text(log)
        Box(
            Modifier
                .size(200.dp)
                .background(Color.Green)
                .pointerInput(Unit) {
                    awaitEachGesture {
                        awaitFirstDown().also {
                            it.consume()
                        }
                        val up = waitForUpOrCancellation()
                        if (up != null) {
                            log = "click+ ${System.currentTimeMillis()}"
                        }
                    }
                }
        )
    }
}

@Preview
@Composable
fun LogPointerEventsConsumption() {
    var log by remember { mutableStateOf("") }
    Column {
        Text(log)
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Green)
                .pointerInput(Unit) {
                    awaitEachGesture {
                        while (true) {
                            /*val event = awaitPointerEvent()
                            log = "Outer+ ${System.currentTimeMillis()}"
                            println(log)
                            event.changes.forEach { it.consume() }*/

                            val event = awaitPointerEvent()
                            log = "Outer+ ${System.currentTimeMillis()}"
                            println(log)
                            if (event.changes.any { it.isConsumed }) {

                            } else {

                            }
                        }
                    }
                }
        ) {
            Box(
                Modifier
                    .size(200.dp)
                    .background(Color.Red)
                    .pointerInput(Unit) {
                        awaitEachGesture {
                            while (true) {
                                // inner can receive events, even it has been consumed by outer
                                /*val event = awaitPointerEvent()
                                log = "Inner+ ${System.currentTimeMillis()}"
                                println(log)
                                if (event.changes.any { it.isConsumed }) {

                                } else {

                                }*/

                                val event = awaitPointerEvent()
                                log = "Inner+ ${System.currentTimeMillis()}"
                                println(log)
                                event.changes.forEach { it.consume() }
                            }
                        }
                    }
            )
        }
    }
}


@Preview
@Composable
fun LogPointerEventsPropagation() {
    var log by remember { mutableStateOf("") }
    Column {
        Text(log)
        Box(
            Modifier
                .fillMaxSize()
                .background(Color.Green)
                .pointerInput(Unit) {
                    awaitEachGesture {
                        while (true) {
                            val eventInitial = awaitPointerEvent(PointerEventPass.Initial)
                            log =
                                "Initial ${eventInitial.type}, ${eventInitial.changes.first().position}"
                            println(log)

                            val eventMain = awaitPointerEvent(PointerEventPass.Main)
                            log = "Main ${eventMain.type}, ${eventMain.changes.first().position}"
                            println(log)

                            val eventFinal = awaitPointerEvent(PointerEventPass.Final)
                            log = "Final ${eventFinal.type}, ${eventFinal.changes.first().position}"
                            println(log)
                        }
                    }
                }
        )
    }
}

