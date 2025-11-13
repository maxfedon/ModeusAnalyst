package com.asteroiddd.modeusanalyst.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import com.asteroiddd.modeusanalyst.source.utils.setTextColor
import com.asteroiddd.modeusanalyst.ui.theme.Typography

@Composable
fun ModuleElement(
    name: String,
    score: String = "0",
    mark: String = "-",
    clickable: Boolean = false,
    onClick: () -> Unit = {},
    style: TextStyle = TextStyle()
) {
    Container (
        clickable = clickable,
        onClick = onClick
    ) {
        Row (modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name,
                style = Typography.bodyMedium.merge(style),
                modifier = Modifier
                    .weight(4f)
            )
            Text(
                text = score,
                color = setTextColor(score, "score"),
                style = Typography.bodyMedium.copy(textAlign = TextAlign.Center).merge(style),
                modifier = Modifier
                    .weight(1f)
            )
            Text(text = mark,
                color = setTextColor(mark, "mark"),
                style = Typography.bodyMedium.copy(textAlign = TextAlign.Center).merge(style),
                modifier = Modifier
                    .weight(1f)
            )
        }
    }
}