package com.asteroiddd.modeusanalyst.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asteroiddd.modeusanalyst.source.model.Auth
import com.asteroiddd.modeusanalyst.source.repository.AuthRepository
import com.asteroiddd.modeusanalyst.source.repository.SettingsRepository
import com.asteroiddd.modeusanalyst.ui.component.Container
import com.asteroiddd.modeusanalyst.ui.component.Input
import com.asteroiddd.modeusanalyst.ui.component.Screen
import com.asteroiddd.modeusanalyst.ui.theme.Black
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun EntryScreen(
    onLogin: () -> Unit
) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val settingsRepository = remember { SettingsRepository(context) }
    val coroutineScope = rememberCoroutineScope()

    val savedAuth by authRepository.getAuth().collectAsState(initial = null)
    val host by settingsRepository.hostFlow.collectAsState(initial = "")

    var usernameText by remember { mutableStateOf("") }
    var passwordText by remember { mutableStateOf("") }
    var hostText by remember { mutableStateOf("") }

    LaunchedEffect(savedAuth) {
        savedAuth?.let {
            usernameText = it.username
            passwordText = it.password
        }
    }
    LaunchedEffect(host) {
        hostText = host
    }

    Screen(
        modifier = Modifier
            .background(Black)
    ) {
        Spacer(
            modifier = Modifier
                .padding(top = 24.dp)
        )
        Text(
            text = "Вход через\nучётную запись\nТюмГУ",
            style = Typography.titleMedium.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(PaddingSmall)) {
            Container {
                Input(
                    value = usernameText,
                    onValueChange = { usernameText = it },
                    placeholder = "Имя пользователя"
                )
            }

            Container {
                Input(
                    value = passwordText,
                    onValueChange = { passwordText = it },
                    placeholder = "Пароль",
                    visualTransformation = PasswordVisualTransformation()
                )
            }

            Container {
                Input(
                    value = hostText,
                    onValueChange = { hostText = it },
                    placeholder = "Хост"
                )
            }

            Container(
                clickable = true,
                onClick = {
                    coroutineScope.launch {
                        authRepository.saveAuth(Auth(username = usernameText, password = passwordText))
                        settingsRepository.saveHost(hostText)
                        onLogin()
                    }
                }
            ) {
                Text("Войти")
            }
        }
    }
}