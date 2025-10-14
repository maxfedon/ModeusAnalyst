package com.asteroiddd.modeusanalyst.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
fun Block(
    contentAlignment: Alignment = Alignment.CenterStart,
    content: @Composable (() -> Unit),
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(Shapes.medium)
            .background(DarkGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 64.dp)
                .padding(PaddingMedium)
                .background(DarkGray),
            contentAlignment = contentAlignment
        ) {
            content()
        }
    }
}