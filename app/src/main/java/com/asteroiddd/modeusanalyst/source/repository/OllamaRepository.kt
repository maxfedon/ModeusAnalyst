package com.asteroiddd.modeusanalyst.source.repository

import com.asteroiddd.modeusanalyst.source.model.OllamaRequest
import com.asteroiddd.modeusanalyst.source.model.OllamaSimpleResponse
import io.ktor.client.* 
import io.ktor.client.engine.cio.* 
import io.ktor.client.plugins.contentnegotiation.* 
import io.ktor.client.request.* 
import io.ktor.client.statement.*
import io.ktor.http.* 
import io.ktor.serialization.kotlinx.json.* 
import io.ktor.utils.io.* 
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

class OllamaRepository(private val settingsRepository: SettingsRepository) {
    var port = "10001"

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        engine {
            requestTimeout = 0L
            endpoint {
                connectTimeout = 300_000
                connectAttempts = 5
            }
        }
    }

    suspend fun generate(prompt: String, model: String = ""): Result<String> {
        if (prompt.isBlank()) return Result.failure(IllegalArgumentException("Prompt cannot be empty"))

        val request = OllamaRequest(model = model, prompt = prompt, stream = true)
        val host = settingsRepository.hostFlow.first()

        return try {
            val httpResponse: HttpResponse = client.post("http://$host:$port/api/generate") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }

            val channel: ByteReadChannel = httpResponse.bodyAsChannel()
            val textBuilder = StringBuilder()

            while (!channel.isClosedForRead) {
                val line = channel.readUTF8Line()
                if (!line.isNullOrBlank()) {
                    try {
                        val part = Json { ignoreUnknownKeys = true }.decodeFromString<OllamaSimpleResponse>(line)
                        textBuilder.append(part.response)
                    } catch (_: Exception) {

                    }
                }
            }

            Result.success(textBuilder.toString())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun checkConnection(): Result<Unit> {
        val host = settingsRepository.hostFlow.first()
        return try {
            client.get("http://$host:$port/")
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}