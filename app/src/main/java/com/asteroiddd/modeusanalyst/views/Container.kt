package com.asteroiddd.modeusanalyst.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.asteroiddd.modeusanalyst.ui.theme.DarkGray
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.Shapes

@Composable
fun Container(
    clickable: Boolean = false,
    onClick: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(Shapes.medium)
            .background(DarkGray)
            .then(if (clickable) Modifier.clickable { onClick() } else Modifier )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(PaddingMedium)
                .background(DarkGray),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}