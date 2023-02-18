package com.beva.compose_playground_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
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
        Box(
            modifier = Modifier
                .fillMaxHeight(0.36f)
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
            ) {
                StarBox()
//                ControlBox()
//                ColorBox()
            }
        }
    }
}

@Composable
fun StarBox(
) {
    var displayDefault by remember { mutableStateOf(false) }
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //the svg image can load by adding new vector within local file
        Image(
            painter = painterResource(
                id = if (displayDefault) R.drawable.icon_star_filled else R.drawable.icon_star_line
            ),
            contentDescription = "Empty Star",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.CenterHorizontally)
                .clickable {
                    displayDefault = !displayDefault
                },
        )

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
                        },
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = if (displayDefault) "Default" else "",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.clickable {
                    displayDefault =!displayDefault
                },

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
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black)
                    .padding(8.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .border(2.dp, Color.Black)
                    .padding(8.dp)
                    .background(Color.Red)
            )
        }

    }
}

@Composable
fun ControlBox() {
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
                    },
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = "Default",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.clickable {

            },

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
                }
            )
        }
    }
}

@Composable
fun ColorBox() {
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
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .border(2.dp, Color.Black)
                .padding(8.dp)
                .background(Color.Green)
        )
        Box(
            modifier = Modifier
                .size(80.dp)
                .border(2.dp, Color.Black)
                .padding(8.dp)
                .background(Color.Red)
        )
    }
}
