package com.asteroiddd.modeusanalyst.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium

@Composable
fun Screen(content: @Composable () -> Unit) {
    Column(modifier =  Modifier
        .padding(PaddingMedium)
    ) {
        content()
    }
}
