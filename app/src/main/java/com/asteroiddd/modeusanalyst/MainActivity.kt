package com.asteroiddd.modeusanalyst

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import com.asteroiddd.modeusanalyst.ui.theme.DarkGray
import com.asteroiddd.modeusanalyst.ui.theme.ModeusAnalystTheme
import com.asteroiddd.modeusanalyst.views.ControlBar
import com.asteroiddd.modeusanalyst.views.MarksScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemUiController = rememberSystemUiController()
            val useDarkIcons = false
            SideEffect {
                systemUiController.setSystemBarsColor(
                    color = DarkGray,
                    darkIcons = useDarkIcons
                )
            }
            ModeusAnalystTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    bottomBar = {
                        Column (
                            modifier = Modifier
                                .background(DarkGray)
                        ) {
                            ControlBar()
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .navigationBarsPadding()
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .padding(innerPadding)
                    ) {

                        //EntryScreen()
                        MarksScreen()
                        //SettingsScreen()

                    }
                }
            }
        }
    }
}