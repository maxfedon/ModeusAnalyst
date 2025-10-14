package com.asteroiddd.modeusanalyst.ui.views.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import com.asteroiddd.modeusanalyst.ui.views.Container
import com.asteroiddd.modeusanalyst.ui.views.Screen

@Composable
fun SettingsScreen() {
    Screen {
        Text(
            text = "Настройки",
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(bottom = PaddingMedium)
        )
        Column(verticalArrangement = Arrangement.spacedBy(PaddingSmall)) {
            Container(
                clickable = true,
                onClick = {},
                contentAlignment = Alignment.CenterStart
            ) {
                Text("Выйти из профиля")
            }
        }
    }
}