package com.asteroiddd.modeusanalyst.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.asteroiddd.modeusanalyst.R

val Inter = FontFamily(
    Font(R.font.inter_regular, FontWeight.Normal),
    Font(R.font.inter_bold, FontWeight.Bold)
)

val Common = TextStyle(
    fontFamily = Inter,
    fontWeight = FontWeight.Normal,
    color = White,
    letterSpacing = 0.5.sp
)

val Typography = Typography(

    // Common
    bodyMedium = Common.merge(TextStyle(
        fontSize = 14.sp,
        lineHeight = 14.sp,
    )),

    // Title
    titleMedium = Common.merge(TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 28.sp,
    )),

    // ModuleName
    labelMedium = Common.merge(TextStyle(
        fontSize = 12.sp,
        lineHeight = 12.sp,
    )),
)