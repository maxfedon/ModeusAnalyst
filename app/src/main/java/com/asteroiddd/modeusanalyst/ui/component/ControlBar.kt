package com.asteroiddd.modeusanalyst.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.asteroiddd.modeusanalyst.R
import com.asteroiddd.modeusanalyst.ui.theme.DarkGray

@Composable
fun ControlBar(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(DarkGray),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ControlButton(id = R.drawable.line_chart, desc = "Analyse", onClick = { navController.navigate("course") })
        ControlButton(id = R.drawable.home, desc = "Home", onClick = { navController.navigate("marks") })
        ControlButton(id = R.drawable.gear, desc = "Setting", onClick = { navController.navigate("settings") })
    }
}

@Composable
fun ControlButton(id: Int, desc: String, onClick: () -> Unit) {
    Button (
        onClick = onClick,
        modifier = Modifier
            .size(56.dp),
        colors = ButtonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.White
        ),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(id),
            contentDescription = desc,
            modifier = Modifier
                .height(32.dp)
        )
    }
}