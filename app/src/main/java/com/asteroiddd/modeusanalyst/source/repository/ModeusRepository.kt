package com.asteroiddd.modeusanalyst.source.repository

import android.content.Context
import com.asteroiddd.modeusanalyst.source.database.AppDatabase
import com.asteroiddd.modeusanalyst.source.model.AuthRequest
import com.asteroiddd.modeusanalyst.source.model.ModuleResult
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.jsoup.Jsoup

class ModeusRepository(
    context: Context,
    private val settingsRepository: SettingsRepository,
    private val authRepository: AuthRepository
) {
    var port = "10002"

    private val moduleResultDao = AppDatabase.getDatabase(context).moduleResultDao()

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 30_000
        }
    }

    fun getMyResults(): Flow<List<ModuleResult>> = moduleResultDao.getAll()

    suspend fun refreshResults(): String? {
        return try {
            val host = settingsRepository.hostFlow.first()
            val auth = authRepository.getAuth().first()!!

            val responseText = client.post("http://$host:$port/get_results") {
                contentType(ContentType.Application.Json)
                setBody(AuthRequest(
                    username = auth.username,
                    password = auth.password
                ))
            }.bodyAsText()

            val json = Json.parseToJsonElement(responseText).jsonObject
            val html = json["html"]?.jsonPrimitive?.content!!

            val results = parseResults(html)

            moduleResultDao.deleteAll()
            moduleResultDao.insertAll(results)

            null
        } catch (e: Exception) {
            e.printStackTrace()
            e.message
        }
    }

    private fun parseResults(html: String): List<ModuleResult> {
        val doc = Jsoup.parse(html)
        val moduleResults = mutableListOf<ModuleResult>()

        val courseElements = doc.select(".p-datatable-tbody tr.ng-star-inserted")

        for (element in courseElements) {
            val name = element.select("a").first()?.text()?.trim()
            val score = element.select("td.current-result span").first()?.text()?.trim() ?: ""
            val mark = element.select("td.final-result span").first()?.text()?.trim() ?: ""
            val grades = element.select(".lesson-realization.ng-star-inserted")
                .mapNotNull { it.text()?.trim() }
                .filter { it.toIntOrNull() != null }

            if (name != null) {
                moduleResults.add(ModuleResult(name, score, mark, grades))
            }
        }

        return moduleResults
    }
}
