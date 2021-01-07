package com.sw.mobile.flickrbrowser.ui

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.font
import androidx.compose.ui.text.font.fontFamily
import androidx.compose.ui.unit.sp
import com.sw.mobile.flickrbrowser.R

private val Dank = fontFamily(
    font(R.font.dankmono_italic, style = FontStyle.Italic),
    font(R.font.dankmono_regular),
)

val FlickrTypography = Typography(
    h4 = TextStyle(
        fontFamily = Dank,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp
    ),
    h5 = TextStyle(
        fontFamily = Dank,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),
    h6 = TextStyle(
        fontFamily = Dank,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Dank,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Dank,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Dank,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = Dank,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = Dank,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
    overline = TextStyle(
        fontFamily = Dank,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    )
)
//val FlickrTypography = Typography(defaultFontFamily = Dank)
