package com.asteroiddd.modeusanalyst.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.asteroiddd.modeusanalyst.R

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val Common = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Normal,
    textAlign = TextAlign.Start,
    color = White,
    letterSpacing = 0.5.sp
)

val Typography = Typography(

    // Common
    bodyMedium = Common.merge(TextStyle(
        fontSize = 14.sp,
        lineHeight = 14.sp,
    )),

    // Text
    bodyLarge = Common.merge(TextStyle(
        fontSize = 16.sp,
        lineHeight = 20.sp,
    )),

    // Screen title
    titleMedium = Common.merge(TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 26.sp,
        lineHeight = 30.sp,
    )),

    // Block title
    titleSmall = Common.merge(TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        lineHeight = 24.sp,
    )),

    // ModuleName
    labelMedium = Common.merge(TextStyle(
        fontSize = 12.sp,
        lineHeight = 12.sp,
    )),
)