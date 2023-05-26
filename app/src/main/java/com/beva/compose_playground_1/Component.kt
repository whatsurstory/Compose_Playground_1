package com.beva.compose_playground_1

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beva.compose_playground_1.ui.theme.StarColor
import kotlin.math.roundToInt


const val TAG = "Beva"
@Composable
fun StarBox() {

    var displayDefault by remember { mutableStateOf(false) }
    var degree by remember { mutableStateOf(0f) }
    var originColor by remember { mutableStateOf(StarColor) }
    var originImage by remember { mutableStateOf(R.drawable.icon_star_line) }

    val rotation by animateFloatAsState(
        targetValue = degree,
        animationSpec = tween(100, easing = LinearEasing)
    )

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //the svg image can load by adding new vector within local file
        var offsetX by remember {
            mutableStateOf(0f)
        }

        var offsetY by remember {
            mutableStateOf(0f)
        }

        val originPosition = remember {
            mutableStateOf(Offset.Zero)
        }


        Box(
            modifier = Modifier
                .offset { IntOffset(offsetX.roundToInt(), offsetY.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures(
                        onDrag = { change, dragAmount ->
                            change.consume()
                            offsetX += dragAmount.x
                            Log.d(TAG, "StarBox: x: $offsetX")
                            offsetY += dragAmount.y
                            Log.d(TAG, "StarBox: y: $offsetY")
                            if (offsetX != 0f || offsetY != 0f) originColor = Color.Black
                        },
                        onDragEnd = {
                            offsetX = Offset.Zero.x
                            offsetY = Offset.Zero.y
                        })
                }
        ) {

            Icon(
                painter = painterResource(id = originImage),
                tint = originColor,
                contentDescription = "Empty Star",
                modifier = Modifier
                    .size(80.dp)
                    .clickable {
                        originImage = R.drawable.icon_star_filled
                    }
                    .graphicsLayer {
                        rotationZ = rotation
                    }
            )
        }


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_refresh_right),
                    contentDescription = "turn left",
                    modifier = Modifier
                        .graphicsLayer {
                            rotationY = 180f
                        }
                        .clickable {
                            degree -= 30f
                        },
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if (degree != 0f || originColor != StarColor || originImage != R.drawable.icon_star_line) "Default" else "",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    degree = 0f
                    originColor = StarColor
                    originImage = R.drawable.icon_star_line
                    displayDefault = !displayDefault
                }

            )
            Spacer(modifier = Modifier.width(12.dp))
            Box(modifier = Modifier) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_refresh_right
                    ),
                    contentDescription = "turn right",
                    modifier = Modifier.clickable {
                        degree += 30f
                    }
                )
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black)
                    .padding(8.dp)
                    .background(Color.Blue)
                    .clickable {
                        originColor = Color.Blue
                    }

            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black)
                    .padding(8.dp)
                    .background(Color.Green)
                    .clickable {
                        originColor = Color.Green
                    }
            )
//            { isInBound: Boolean ->
//                if (isInBound) {
//                    originColor = Color.Green
//                }
//            }
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black)
                    .padding(8.dp)
                    .background(Color.Red)
                    .clickable {
                        originColor = Color.Red
                    }
            )
//            { isInBound: Boolean ->
//                if (isInBound) {
//                    originColor = Color.Red
//                }
//            }
        }
    }
}


