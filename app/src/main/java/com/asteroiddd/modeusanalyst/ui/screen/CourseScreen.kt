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
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import com.asteroiddd.modeusanalyst.ui.component.Block
import com.asteroiddd.modeusanalyst.ui.component.Screen

@Composable
fun CourseScreen() {
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
                    Text(
                        text = "58.5",
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
                    Text(
                        text = "4.67",
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