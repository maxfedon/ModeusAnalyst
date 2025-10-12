package com.asteroiddd.modeusanalyst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.asteroiddd.modeusanalyst.ui.theme.ModeusAnalystTheme
import com.asteroiddd.modeusanalyst.views.Entry

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModeusAnalystTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Entry(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}