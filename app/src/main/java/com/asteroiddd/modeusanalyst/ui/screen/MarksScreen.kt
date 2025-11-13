package com.asteroiddd.modeusanalyst.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.asteroiddd.modeusanalyst.source.repository.AuthRepository
import com.asteroiddd.modeusanalyst.source.repository.ModeusRepository
import com.asteroiddd.modeusanalyst.source.repository.SettingsRepository
import com.asteroiddd.modeusanalyst.source.service.MarkService
import com.asteroiddd.modeusanalyst.ui.component.Screen
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
import com.asteroiddd.modeusanalyst.ui.theme.Typography

@Composable
fun MarksScreen(navController: NavController) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val settingsRepository = remember { SettingsRepository(context) }
    val modeusRepository = remember { ModeusRepository(context, settingsRepository, authRepository) }
    val markService = remember { MarkService() }

    val moduleResults by modeusRepository.getMyResults().collectAsState(initial = emptyList())

    Screen {
        Text(
            text = "Текущая успеваемость",
            style = Typography.titleMedium,
            modifier = Modifier
                .padding(bottom = PaddingMedium)
        )

        markService.ModuleList(results = moduleResults, navController = navController)
    }
}
