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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asteroiddd.modeusanalyst.source.repository.AuthRepository
import com.asteroiddd.modeusanalyst.source.repository.ModeusRepository
import com.asteroiddd.modeusanalyst.source.repository.SettingsRepository
import com.asteroiddd.modeusanalyst.ui.component.ControlBar
import com.asteroiddd.modeusanalyst.ui.screen.CourseScreen
import com.asteroiddd.modeusanalyst.ui.screen.EntryScreen
import com.asteroiddd.modeusanalyst.ui.screen.MarksScreen
import com.asteroiddd.modeusanalyst.ui.screen.ModuleScreen
import com.asteroiddd.modeusanalyst.ui.screen.SettingsScreen
import com.asteroiddd.modeusanalyst.ui.theme.Black
import com.asteroiddd.modeusanalyst.ui.theme.DarkGray
import com.asteroiddd.modeusanalyst.ui.theme.ModeusAnalystTheme
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.Typography
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Suppress("DEPRECATION")
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModeusAnalystTheme {
                App()
            }
        }
    }
}

@Composable
@Suppress("DEPRECATION")
private fun App() {
    val systemUiController = rememberSystemUiController()
    val useDarkIcons = false
    SideEffect {
        systemUiController.setSystemBarsColor(
            color = DarkGray,
            darkIcons = useDarkIcons
        )
    }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        modifier = Modifier
            .fillMaxSize(),
        startDestination = "entry"
    ) {
        composable("entry") {
            EntryScreen(
                onLogin = {
                    navController.navigate("main") {
                        popUpTo("entry") { inclusive = true }
                    }
                }
            )
        }
        composable("main") {
            MainContent()
        }
    }
}

@Composable
private fun MainContent() {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val settingsRepository = remember { SettingsRepository(context) }
    val modeusRepository = remember { ModeusRepository(context, settingsRepository, authRepository) }

    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            isLoading = true
            errorMessage = modeusRepository.refreshResults()
        } catch (e: Exception) {
            errorMessage = e.message
        } finally {
            isLoading = false
        }
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        errorMessage != null -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Ошибка!\nПопробуйте позже.\n($errorMessage)",
                    style = Typography.titleMedium.copy(textAlign = TextAlign.Center),
                    modifier = Modifier
                        .padding(bottom = PaddingMedium)
                )
            }
        }

        else -> {
            val navController = rememberNavController()
            Scaffold(
                modifier = Modifier
                    .fillMaxSize(),
                bottomBar = {
                    Column(
                        modifier = Modifier
                            .background(DarkGray)
                    ) {
                        ControlBar(navController = navController)
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding()
                                .navigationBarsPadding()
                        )
                    }
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .padding(
                            top = innerPadding.calculateTopPadding(),
                            bottom = innerPadding.calculateBottomPadding() - PaddingMedium - 1.dp
                        )
                ) {
                    MainNavigation(navController)
                }
            }
        }
    }
}

@Composable
private fun MainNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "marks") {
        composable("course") { CourseScreen() }
        composable("marks") { MarksScreen(navController) }
        composable("settings") { SettingsScreen() }
        composable(
            route = "module/{name}/{score}/{mark}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("score") { type = NavType.StringType },
                navArgument("mark") { type = NavType.StringType })
        ) {
            val name = it.arguments?.getString("name") ?: ""
            val score = it.arguments?.getString("score") ?: "0"
            val mark = it.arguments?.getString("mark") ?: "-"
            ModuleScreen(name = name, score = score, mark = mark)
        }
    }
}