package com.asteroiddd.modeusanalyst.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.asteroiddd.modeusanalyst.ui.component.ModuleElement
import com.asteroiddd.modeusanalyst.ui.component.Screen
import com.asteroiddd.modeusanalyst.ui.theme.LightGray
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography

@Composable
fun MarksScreen(navController: NavController) {
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
                clickable = true,
                onClick = {
                    navController.navigate("module/Информационно-технологические решения на базе C# и Java/91/5")
                }
            )
            ModuleElement(
                name = "Администрирование информационных систем",
                clickable = true,
                onClick = {
                    navController.navigate("module/Администрирование информационных систем/0/-")
                }
            )
            ModuleElement(
                name = "Web-ориентированные информационные системы",
                clickable = true,
                onClick = {
                    navController.navigate("module/Web-ориентированные информационные системы/0/-")
                }
            )
        }
    }
}