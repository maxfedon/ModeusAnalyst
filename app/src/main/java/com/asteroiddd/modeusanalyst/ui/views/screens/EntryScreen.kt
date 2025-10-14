package com.asteroiddd.modeusanalyst.ui.views.screens

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

@Composable
fun EntryScreen() {
    val mail = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    _root_ide_package_.com.asteroiddd.modeusanalyst.ui.views.Screen {
        Text(
            text = "Вход через учётную запись\nТюмГУ",
            style = Typography.titleMedium.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 100.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(PaddingSmall)) {
            _root_ide_package_.com.asteroiddd.modeusanalyst.ui.views.Container {
                Input(
                    mail,
                    "username@utmn.study.ru"
                )
            }

            _root_ide_package_.com.asteroiddd.modeusanalyst.ui.views.Container {
                Input(
                    password,
                    "Пароль"
                )
            }

            _root_ide_package_.com.asteroiddd.modeusanalyst.ui.views.Container(
                clickable = true,
                onClick = {}
            ) {
                Text("Войти")
            }
        }
    }
}

@Composable
fun Input(
    text: MutableState<String>,
    placeholder: String,
) {
    BasicTextField(
        value = text.value,
        onValueChange = { text.value = it },
        textStyle = Typography.bodyMedium.copy(color = White),
        cursorBrush = SolidColor(White),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentAlignment = Alignment.CenterStart
            ) {
                if (text.value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = Color.Gray,
                        style = Typography.bodyMedium
                    )
                }
                innerTextField()
            }
        }
    )
}

