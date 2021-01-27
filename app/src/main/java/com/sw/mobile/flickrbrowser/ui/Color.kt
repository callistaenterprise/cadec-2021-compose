package com.sw.mobile.flickrbrowser.ui

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Orange700 = Color(0xFFFFA000)
val Orange800 = Color(0xFFFF8F00)
val Orange900 = Color(0xFFFF6F00)

val Orange200 = Color(0xFFFFE082)
val Orange300 = Color(0xFFFFE082)

val LightColors = lightColors(
    primary = Orange700,
    primaryVariant = Orange900,
    onPrimary = Color.White,
    secondary = Orange700,
    secondaryVariant = Orange200,
    onSecondary = Color.White,
    error = Orange800
)
val DarkColors = darkColors(
    primary = Orange300,
    primaryVariant = Orange700,
    onPrimary = Color.Black,
    secondary = Orange300,
    onSecondary = Color.White,
    error = Orange200
)