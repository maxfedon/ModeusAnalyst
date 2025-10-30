package com.asteroiddd.modeusanalyst.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.asteroiddd.modeusanalyst.ui.theme.PaddingSmall
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import com.asteroiddd.modeusanalyst.ui.theme.White
import com.asteroiddd.modeusanalyst.ui.component.Container
import com.asteroiddd.modeusanalyst.ui.component.Input
import com.asteroiddd.modeusanalyst.ui.component.Screen

@Composable
fun EntryScreen() {
    val mail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Screen {
        Text(
            text = "Вход через учётную запись\nТюмГУ",
            style = Typography.titleMedium.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(PaddingSmall)) {
            Container {
                Input(
                    value = mail.value,
                    placeholder = "username@utmn.study.ru",
                    onValueChange = { mail.value = it }
                )
            }

            Container {
                Input(
                    value = password.value,
                    placeholder = "Пароль",
                    onValueChange = { password.value = it }
                )
            }

            Container(
                clickable = true,
                onClick = {}
            ) {
                Text("Войти")
            }
        }
    }
}
