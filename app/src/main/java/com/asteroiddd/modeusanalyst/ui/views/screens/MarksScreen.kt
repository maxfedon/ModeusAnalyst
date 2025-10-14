package com.asteroiddd.modeusanalyst.ui.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.asteroiddd.modeusanalyst.ui.theme.LightGray
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import com.asteroiddd.modeusanalyst.ui.views.ModuleElement
import com.asteroiddd.modeusanalyst.ui.views.Screen

@Composable
fun MarksScreen() {
    Screen {
        Text(
            text = "Текущая успеваемость",
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(bottom = PaddingMedium)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(PaddingSmall)
        ) {
            ModuleElement(
                name = "Модуль",
                score = "Баллы",
                mark = "Оценка",
                style = TextStyle(color = LightGray)
            )
            ModuleElement(
                name = "Информационно-технологические решения на базе C# и Java",
                score = "91",
                mark = "5",
                clickable = true
            )
            ModuleElement(
                name = "Администрирование информационных систем",
                clickable = true
            )
            ModuleElement(
                name = "Web-ориентированные информационные системы",
                clickable = true
            )
        }
    }
}