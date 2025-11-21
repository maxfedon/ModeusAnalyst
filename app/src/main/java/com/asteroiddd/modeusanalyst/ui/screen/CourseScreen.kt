package com.asteroiddd.modeusanalyst.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.asteroiddd.modeusanalyst.source.repository.AuthRepository
import com.asteroiddd.modeusanalyst.source.repository.ModeusRepository
import com.asteroiddd.modeusanalyst.source.repository.SettingsRepository
import com.asteroiddd.modeusanalyst.source.utils.setTextColor
import com.asteroiddd.modeusanalyst.ui.component.Block
import com.asteroiddd.modeusanalyst.ui.component.PieChart
import com.asteroiddd.modeusanalyst.ui.component.PieChartData
import com.asteroiddd.modeusanalyst.ui.component.Screen
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography

@Composable
fun CourseScreen() {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val settingsRepository = remember { SettingsRepository(context) }
    val modeusRepository = remember { ModeusRepository(context, settingsRepository, authRepository) }

    val moduleResults by modeusRepository.getMyResults().collectAsState(initial = emptyList())

    val scores = remember(moduleResults) {
        moduleResults.mapNotNull { it.score.replace(',', '.').toFloatOrNull() }.reversed()
    }

    val averageScore = scores.takeIf { it.isNotEmpty() }?.average()?.toFloat() ?: 0f

    val averageMark = moduleResults
        .mapNotNull { it.mark.toIntOrNull() }
        .takeIf { it.isNotEmpty() }
        ?.average() ?: 0.0

    val pieChartData = remember(moduleResults) {
        moduleResults.mapNotNull {
            val score = it.score.replace(',', '.').toFloatOrNull()
            if (score != null) {
                PieChartData(label = it.name, value = score)
            } else {
                null
            }
        }
    }

    Screen {
        Text(
            text = "Общая успеваемость",
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(bottom = PaddingMedium)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(PaddingSmall)
        ) {
            Block {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Средний балл",
                        style = Typography.titleSmall
                    )
                    val scoreText = if (averageScore == 0.0f) "-" else String.format("%.2f", averageScore)
                    Text(
                        text = scoreText,
                        color = setTextColor(scoreText, "score"),
                        style = Typography.bodyLarge
                    )
                }
            }
            Block {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Средняя оценка",
                        style = Typography.titleSmall
                    )
                    val markText = if (averageMark == 0.0) "-" else String.format("%.2f", averageMark)
                    Text(
                        text = markText,
                        color = setTextColor(markText, "mark"),
                        style = Typography.bodyLarge
                    )
                }
            }
            Block {
                Column {
                    Text(
                        text = "Распределение баллов по модулям",
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .padding(bottom = PaddingMedium)
                    )
                    if (pieChartData.isNotEmpty()) {
                        PieChart(data = pieChartData)
                    } else {
                        Text(
                            text = "Нет данных для отображения диаграммы",
                            style = Typography.bodyLarge
                        )
                    }
                }
            }
            Block {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Комментарий",
                        style = Typography.titleSmall
                    )
                    Text(
                        text = "...",
                        style = Typography.bodyLarge
                    )
                }
            }
        }
    }
}
