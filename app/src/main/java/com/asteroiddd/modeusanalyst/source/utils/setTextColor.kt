package com.asteroiddd.modeusanalyst.source.utils

import androidx.compose.ui.graphics.Color
import com.asteroiddd.modeusanalyst.ui.theme.Green
import com.asteroiddd.modeusanalyst.ui.theme.LightGray
import com.asteroiddd.modeusanalyst.ui.theme.Orange
import com.asteroiddd.modeusanalyst.ui.theme.Red
import com.asteroiddd.modeusanalyst.ui.theme.Yellow

fun setTextColor(text: String, type: String): Color {
    if (text == "-" || text == "0") return LightGray

    val value = text.toFloatOrNull() ?: return LightGray

    return when (type) {
        "score" -> {
            when {
                value < 61 -> Red
                value in 61.0..75.0 -> Orange
                value in 76.0..90.0 -> Yellow
                value > 90 -> Green
                else -> LightGray
            }
        }
        "mark" -> {
            when {
                value < 2.5 -> Red
                value in 2.5..3.5 -> Orange
                value in 3.5..4.5 -> Yellow
                value > 4.5 -> Green
                else -> LightGray
            }
        }
        else -> LightGray
    }
}