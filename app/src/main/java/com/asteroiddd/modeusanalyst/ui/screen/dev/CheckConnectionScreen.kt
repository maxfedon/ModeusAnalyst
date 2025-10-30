package com.asteroiddd.modeusanalyst.ui.screen.dev

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.asteroiddd.modeusanalyst.source.repository.OllamaRepository
import com.asteroiddd.modeusanalyst.source.repository.SettingsRepository
import kotlinx.coroutines.launch

@Composable
fun CheckConnectionScreen() {
    val context = LocalContext.current
    val settingsRepository = remember { SettingsRepository(context) }
    val ollamaRepository = remember { OllamaRepository(settingsRepository) }

    var connectionStatus by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            coroutineScope.launch {
                val result = ollamaRepository.checkConnection()
                connectionStatus = if (result.isSuccess) {
                    "Connection successful!"
                } else {
                    "Connection failed: ${result.exceptionOrNull()?.message}"
                }
            }
        }) {
            Text("Check Connection")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = connectionStatus)
    }
}