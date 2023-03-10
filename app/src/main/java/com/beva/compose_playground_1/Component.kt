package com.beva.compose_playground_1

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.beva.compose_playground_1.ui.theme.StarColor
import kotlinx.coroutines.flow.collectLatest


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
        DragTarget(modifier = Modifier, dataToDrop = Star()) {

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

            DropItem<ColorBox>(
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black)
                    .padding(8.dp)
                    .background(Color.Blue)
                    .clickable {
                        originColor = Color.Blue
                    }
            ) { isInBound: Boolean ->
                if (isInBound) {
                    originColor = Color.Blue
                }
            }
            DropItem<ColorBox>(
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black)
                    .padding(8.dp)
                    .background(Color.Green)
                    .clickable {
                        originColor = Color.Green
                    }
            ) { isInBound: Boolean ->
                if (isInBound) {
                    originColor = Color.Green
                }
            }
            DropItem<ColorBox>(
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black)
                    .padding(8.dp)
                    .background(Color.Red)
                    .clickable {
                        originColor = Color.Red
                    }
            ) { isInBound: Boolean ->
                if (isInBound) {
                    originColor = Color.Red
                }
            }
        }
    }
}


