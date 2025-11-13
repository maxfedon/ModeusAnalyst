package com.asteroiddd.modeusanalyst.ui.screen.dev

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.asteroiddd.modeusanalyst.source.repository.OllamaRepository
import com.asteroiddd.modeusanalyst.source.repository.SettingsRepository
import com.asteroiddd.modeusanalyst.ui.component.Input
import kotlinx.coroutines.launch

sealed interface GenerateUiState {
    object Idle : GenerateUiState
    object Loading : GenerateUiState
    data class Success(val output: String) : GenerateUiState
    data class Error(val message: String) : GenerateUiState
}

@Composable
fun GenerateScreen() {
    val context = LocalContext.current
    val settingsRepository = remember { SettingsRepository(context) }
    val ollamaRepository = remember { OllamaRepository(settingsRepository) }
    val coroutineScope = rememberCoroutineScope()

    var uiState by remember { mutableStateOf<GenerateUiState>(GenerateUiState.Idle) }

    var prompt by remember { mutableStateOf("") }
    var model by remember { mutableStateOf("Hermes-2-Pro") }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Input(
            value = prompt,
            onValueChange = { prompt = it },
            placeholder = "Prompt",
            modifier = Modifier.fillMaxWidth()
        )
        Input(
            value = model,
            onValueChange = { model = it },
            placeholder = "Model",
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        )
        Button(
            onClick = {
                coroutineScope.launch {
                    uiState = GenerateUiState.Loading
                    val result = ollamaRepository.generate(prompt, model)
                    uiState = result.fold(
                        onSuccess = { GenerateUiState.Success(it) },
                        onFailure = { GenerateUiState.Error(it.message ?: "Unknown error") }
                    )
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Generate")
        }

        when (val state = uiState) {
            is GenerateUiState.Idle -> { /* Do nothing */ }
            is GenerateUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.padding(top = 16.dp))
            }
            is GenerateUiState.Success -> {
                Text(text = state.output, modifier = Modifier.padding(top = 16.dp))
            }
            is GenerateUiState.Error -> {
                Text(text = state.message, modifier = Modifier.padding(top = 16.dp))
            }
        }
    }
}