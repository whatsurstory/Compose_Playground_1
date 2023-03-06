package com.beva.compose_playground_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.beva.compose_playground_1.ui.theme.Compose_Playground_1Theme
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_Playground_1Theme {
                CardBox()
            }
        }
    }
}

@Composable
fun CardBox() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = 8.dp
    ) {
        DraggableScreen(
            modifier = Modifier
                .fillMaxHeight(0.36f)
                .padding(8.dp)
        ) {
            StarBox()
        }
    }
}

@Composable
fun StarBox() {

    var displayDefault by remember { mutableStateOf(false) }
    var degree by remember { mutableStateOf(0f) }
    var originColor by remember { mutableStateOf(Color(0xFFfbbf2f)) }
    var originImage by remember { mutableStateOf(R.drawable.icon_star_line) }

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //the svg image can load by adding new vector within local file
        DragTarget(modifier = Modifier, dataToDrop = Start()) {

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
                        rotationZ = degree
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
                            println("left Clicked")
                            degree += -12f
                        },
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if (degree != 0f || originColor != Color(0xFFfbbf2f) || originImage != R.drawable.icon_star_line) "Default" else "",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    degree = 0f
                    originColor = Color(0xFFfbbf2f)
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
                        println("right Clicked")
                        degree += 12f
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
        }
    }
}

data class Start(
    var image: Int = R.drawable.icon_star_line,
    var tint: Color = Color(0xFFfbbf2f),
    var degree: Float = 0f
)
