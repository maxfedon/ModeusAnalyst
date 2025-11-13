package com.asteroiddd.modeusanalyst.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.asteroiddd.modeusanalyst.source.repository.SettingsRepository
import com.asteroiddd.modeusanalyst.ui.component.Container
import com.asteroiddd.modeusanalyst.ui.component.Input
import com.asteroiddd.modeusanalyst.ui.component.Screen
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    val settingsRepository = remember { SettingsRepository(context) }
    val coroutineScope = rememberCoroutineScope()

    val savedHost by settingsRepository.hostFlow.collectAsState(initial = "")

    var hostText by remember { mutableStateOf("") }

    LaunchedEffect(savedHost) {
        hostText = savedHost
    }

    Screen {
        Text(
            text = "Настройки",
            style = Typography.titleMedium,
            modifier = Modifier.padding(bottom = PaddingMedium)
        )
        Column(verticalArrangement = Arrangement.spacedBy(PaddingSmall)) {
            Text(
                text = "Сеть",
                style = Typography.titleSmall,
                modifier = Modifier
                    .padding(bottom = PaddingMedium)
            )

            Container(
                clickable = false,
                onClick = {},
                contentAlignment = Alignment.CenterStart
            ) {
                Input(
                    value = hostText,
                    onValueChange = { newHost ->
                        hostText = newHost
                        coroutineScope.launch {
                            settingsRepository.saveHost(newHost)
                        }
                    },
                    placeholder = "Хост",
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}