package com.asteroiddd.modeusanalyst.source.service

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.navigation.NavController
import com.asteroiddd.modeusanalyst.source.model.ModuleResult
import com.asteroiddd.modeusanalyst.ui.component.ModuleElement
import com.asteroiddd.modeusanalyst.ui.theme.Green
import com.asteroiddd.modeusanalyst.ui.theme.LightGray
import com.asteroiddd.modeusanalyst.ui.theme.Orange
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Red
import com.asteroiddd.modeusanalyst.ui.theme.Yellow

class MarkService {
    @Composable
    fun ModuleList(results: List<ModuleResult>, navController: NavController) {
        Column(
            verticalArrangement = Arrangement.spacedBy(PaddingSmall)
        ) {
            ModuleElement(
                name = "Модуль",
                score = "Баллы",
                mark = "Оценка",
                style = TextStyle(color = LightGray)
            )
            results.forEach { result ->
                ModuleElement(
                    name = result.name,
                    score = result.score,
                    mark = result.mark,
                    clickable = true,
                    onClick = {
                        navController.navigate("module/${result.name}/${result.score}/${result.mark}")
                    }
                )
            }
        }
    }
}