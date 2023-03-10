package com.beva.compose_playground_1

import androidx.compose.ui.graphics.Color

data class Star(
    var image: Int = R.drawable.icon_star_line,
    var tint: Color = Color(0xFFfbbf2f),
    var degree: Float = 0f
)
