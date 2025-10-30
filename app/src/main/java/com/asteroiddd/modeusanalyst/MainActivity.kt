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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.asteroiddd.modeusanalyst.ui.component.ControlBar
import com.asteroiddd.modeusanalyst.ui.screen.CourseScreen
import com.asteroiddd.modeusanalyst.ui.screen.MarksScreen
import com.asteroiddd.modeusanalyst.ui.screen.ModuleScreen
import com.asteroiddd.modeusanalyst.ui.screen.SettingsScreen
import com.asteroiddd.modeusanalyst.ui.theme.DarkGray
import com.asteroiddd.modeusanalyst.ui.theme.ModeusAnalystTheme
import com.asteroiddd.modeusanalyst.ui.theme.PaddingMedium
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
                        NavHost(navController = navController, startDestination = "marks") {
                            composable("course") { CourseScreen(commentText = "У тебя есть предметы, где результат уверенный — оценки держатся в районе 85–95 баллов, это высокий уровень, видно, что там ты работаешь системно и разбираешься в материале. Есть дисциплины со средними результатами (около 60–70 баллов): база есть, но нужно больше практики и внимания к деталям. И есть направления, где баллы пока низкие — порядка 40–45, что говорит о серьёзных пробелах и нехватке регулярной подготовки. В целом картина неоднородная: ты способен показывать отличные результаты, но нужно распределить усилия и подтянуть слабые места, иначе они будут тянуть общую успеваемость вниз.") }
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

                        // don't delete then down, it's for test!
                        //EntryScreen()
                        //MarksScreen()
                        /*ModuleScreen(
                            moduleName = "Программная инженерия",
                            commentText = "У тебя по предмету «Информационно-технологические решения на базе C# и Java» сейчас 91 балл — это твёрдая пятёрка. Ты хорошо справляешься с теорией и практикой, видно, что код пишешь уверенно. Чтобы результат был ещё стабильнее, обрати внимание на более сложные темы вроде многопоточности и интеграции с внешними сервисами. В остальном — отличный уровень, продолжай в том же духе.",
                            learningText = "1. Разобрать конспекты и основные лекции, чтобы закрыть пробелы по базовым темам.\n2. Пересмотреть примеры лабораторных работ и сделать повторные решения, даже если они уже сдавались.\n3. Выделить минимум 2–3 раза в неделю на работу над практикой (код, проектные задания).\n4. Обратиться за консультацией к преподавателю: уточнить, какие темы критичнее подтянуть в первую очередь.\n5. Найти учебные материалы или видео по трудным разделам (например, проектирование ПО, UML, методологии разработки) и делать краткие конспекты.\n6. Сдавать все оставшиеся задания максимально вовремя, чтобы набирать баллы."
                        )*/
                        /*CourseScreen(
                            commentText = "У тебя есть предметы, где результат уверенный — оценки держатся в районе 85–95 баллов, это высокий уровень, видно, что там ты работаешь системно и разбираешься в материале. Есть дисциплины со средними результатами (около 60–70 баллов): база есть, но нужно больше практики и внимания к деталям. И есть направления, где баллы пока низкие — порядка 40–45, что говорит о серьёзных пробелах и нехватке регулярной подготовки. В целом картина неоднородная: ты способен показывать отличные результаты, но нужно распределить усилия и подтянуть слабые места, иначе они будут тянуть общую успеваемость вниз."
                        )*/
                        //SettingsScreen()
                        //CheckConnectionScreen()
                        //GenerateScreen()
                    }
                }
            }
        }
    }
}