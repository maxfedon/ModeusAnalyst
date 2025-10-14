package com.asteroiddd.modeusanalyst.ui.views.screens

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
import com.asteroiddd.modeusanalyst.ui.views.Block
import com.asteroiddd.modeusanalyst.ui.views.Screen

@Composable
fun ModuleScreen(
    moduleName: String,
    commentText: String,
    learningText: String
) {
    Screen {
        Text(
            text = moduleName,
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
                        text = "42",
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
                        text = "-",
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
                        text = commentText,
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
                        style = Typography.titleSmall
                    )
                    Text(
                        text =  learningText,
                        style = Typography.bodyLarge
                    )
                }
            }
        }
    }
}