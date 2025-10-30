package com.asteroiddd.modeusanalyst.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium

@Composable
fun Screen(content: @Composable () -> Unit) {
    Column(modifier =  Modifier
        .padding(PaddingMedium)
        .verticalScroll(rememberScrollState())
    ) {
        content()
        Spacer(modifier = Modifier
            .height(PaddingMedium)
        )
    }
}
