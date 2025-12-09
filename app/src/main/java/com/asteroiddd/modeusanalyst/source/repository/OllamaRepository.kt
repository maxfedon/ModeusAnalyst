package com.asteroiddd.modeusanalyst.source.repository

import com.asteroiddd.modeusanalyst.source.model.OllamaRequest
import com.asteroiddd.modeusanalyst.source.model.OllamaSimpleResponse
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.utils.io.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
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

    fun generate(prompt: String, model: String = ""): Flow<String> = flow {
        if (prompt.isBlank()) throw IllegalArgumentException("Prompt cannot be empty")

        val request = OllamaRequest(model = model, prompt = prompt, stream = true)
        val host = settingsRepository.hostFlow.first()
        val maxRetries = 3

        for (attempt in 1..maxRetries) {
            try {
                client.preparePost("http://$host:$port/api/generate") {
                    contentType(ContentType.Application.Json)
                    setBody(request)
                }.execute { httpResponse ->
                    val channel: ByteReadChannel = httpResponse.bodyAsChannel()
                    while (!channel.isClosedForRead) {
                        val line = channel.readUTF8Line()
                        if (!line.isNullOrBlank()) {
                            try {
                                val part = Json { ignoreUnknownKeys = true }.decodeFromString<OllamaSimpleResponse>(line)
                                emit(part.response)
                            } catch (e: Exception) {
                                // Ignore malformed lines
                            }
                        }
                    }
                }
                return@flow // End the flow on successful completion of the request
            } catch (e: ResponseException) {
                if (e.response.status.value == 500 && attempt < maxRetries) {
                    delay(1000)
                    continue
                }
                throw e
            } catch (e: Exception) {
                throw e
            }
        }
        throw Exception("Failed after multiple retries.")
    }
}
