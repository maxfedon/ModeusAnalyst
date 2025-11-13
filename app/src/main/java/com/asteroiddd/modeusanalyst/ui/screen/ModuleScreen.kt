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
import com.asteroiddd.modeusanalyst.source.utils.setTextColor
import com.asteroiddd.modeusanalyst.ui.component.Block
import com.asteroiddd.modeusanalyst.ui.component.Screen
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography

@Composable
fun ModuleScreen(
    name: String,
    score: String,
    mark: String
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
                Text(
                    text = "График",
                    style = Typography.titleSmall
                )
            }
        }
    }
}