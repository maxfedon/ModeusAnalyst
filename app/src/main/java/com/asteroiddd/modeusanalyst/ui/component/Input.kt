package com.asteroiddd.modeusanalyst.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import com.asteroiddd.modeusanalyst.ui.theme.White

@Composable
fun Input(
    value: String,
    placeholder: String,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit = {}
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = Typography.bodyMedium.copy(color = White),
        cursorBrush = SolidColor(White),
        singleLine = true,
        modifier = modifier
            .fillMaxWidth(),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
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