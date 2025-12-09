package com.asteroiddd.modeusanalyst.source.service

import android.content.Context
import com.asteroiddd.modeusanalyst.source.repository.OllamaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.io.IOException

class AnalysisService(private val context: Context, private val ollamaRepository: OllamaRepository) {

    private val model = "Hermes-2-Pro"

    private fun readPrompt(fileName: String): String {
        return try {
            context.assets.open("prompt/$fileName").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    fun getComment(name: String, score: String, grades: List<String>): Flow<String> {
        val promptTemplate = readPrompt("comment.md")
        if (promptTemplate.isBlank()) {
            return flow { emit("Ошибка: Не найден файл 'comment.md' в 'assets/prompt/'.") }
        }

        val prompt = promptTemplate
            .replace("{name}", name)
            .replace("{score}", score)
            .replace("{grades}", grades.joinToString(", "))

        return ollamaRepository.generate(prompt, model)
            .catch { e -> emit("Ошибка: ${e.message}") }
    }

    fun getProgram(name: String, score: String, grades: List<String>): Flow<String> {
        val promptTemplate = readPrompt("program.md")
        if (promptTemplate.isBlank()) {
            return flow { emit("Ошибка: Не найден файл 'program.md' в 'assets/prompt/'.") }
        }

        val prompt = promptTemplate
            .replace("{name}", name)
            .replace("{score}", score)
            .replace("{grades}", grades.joinToString(", "))

        return ollamaRepository.generate(prompt, model)
            .catch { e -> emit("Ошибка: ${e.message}") }
    }

    fun getCourseComment(averageScore: Float, subjects: String): Flow<String> {
        val promptTemplate = readPrompt("summary.md")
        if (promptTemplate.isBlank()) {
            return flow { emit("Ошибка: Не найден файл 'summary.md' в 'assets/prompt/'.") }
        }

        val prompt = promptTemplate
            .replace("{subjects}", subjects)
            .replace("{averageScore}", String.format("%.2f", averageScore))

        return ollamaRepository.generate(prompt, model)
            .catch { e -> emit("Ошибка: ${e.message}") }
    }
}
