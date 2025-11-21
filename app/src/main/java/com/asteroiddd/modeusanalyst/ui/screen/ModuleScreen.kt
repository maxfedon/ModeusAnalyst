package com.asteroiddd.modeusanalyst.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import com.asteroiddd.modeusanalyst.source.utils.setTextColor
import com.asteroiddd.modeusanalyst.ui.component.Block
import com.asteroiddd.modeusanalyst.ui.component.LineChart
import com.asteroiddd.modeusanalyst.ui.component.Screen
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography

@Composable
fun ModuleScreen(
    name: String,
    score: String,
    mark: String,
    grades: List<String>
) {
    Screen {
        Text(
            text = name,
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
                        text = "Баллы",
                        style = Typography.titleSmall
                    )
                    Text(
                        text = score,
                        color = setTextColor(score, "score"),
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
                        text = "Оценка",
                        style = Typography.titleSmall
                    )
                    Text(
                        text = mark,
                        color = setTextColor(mark, "mark"),
                        style = Typography.bodyLarge
                    )
                }
            }
            Block {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Динамика баллов",
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .padding(bottom = PaddingMedium)
                    )
                    val points = grades.mapNotNull { it.toFloatOrNull() }
                    if (points.isNotEmpty()) {
                        LineChart(points)
                    } else {
                        Text(
                            text = "Нет данных",
                            style = Typography.bodyLarge.copy(fontStyle = FontStyle.Italic)
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
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .padding(bottom = PaddingMedium)
                    )
                    Text(
                        text = "...",
                        style = Typography.bodyLarge
                    )
                }
            }
            Block {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                ) {
                    Text(
                        text = "Программа",
                        style = Typography.titleSmall,
                        modifier = Modifier
                            .padding(bottom = PaddingMedium)
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
