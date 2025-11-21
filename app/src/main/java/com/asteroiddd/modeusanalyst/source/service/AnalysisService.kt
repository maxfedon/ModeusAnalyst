package com.asteroiddd.modeusanalyst.source.service

import android.content.Context
import com.asteroiddd.modeusanalyst.source.repository.OllamaRepository
import java.io.IOException

class AnalysisService(private val context: Context, private val ollamaRepository: OllamaRepository) {

    companion object {
        private val commentCache = mutableMapOf<String, String>()
        private val programCache = mutableMapOf<String, String>()
        private val courseCommentCache = mutableMapOf<String, String>()
    }

    private val model = "Hermes-2-Pro"

    private fun readPrompt(fileName: String): String {
        return try {
            context.assets.open("prompt/$fileName").bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    suspend fun getComment(name: String, score: String, grades: List<String>): String {
        val cachedComment = commentCache[name]
        if (cachedComment != null) {
            return cachedComment
        }

        val promptTemplate = readPrompt("comment.md")
        if (promptTemplate.isBlank()) {
            return "Ошибка: Не найден файл 'comment.md' в 'assets/prompt/'."
        }

        val prompt = promptTemplate
            .replace("{name}", name)
            .replace("{score}", score)
            .replace("{grades}", grades.joinToString(", "))

        return ollamaRepository.generate(prompt, model).fold(
            onSuccess = {
                val cleanedResponse = it.trim().removeSurrounding("\"")
                commentCache[name] = cleanedResponse
                cleanedResponse
            },
            onFailure = { "Ошибка: ${it.message}" }
        )
    }

    suspend fun getProgram(name: String, score: String, grades: List<String>): String {
        val cachedProgram = programCache[name]
        if (cachedProgram != null) {
            return cachedProgram
        }

        val promptTemplate = readPrompt("program.md")
        if (promptTemplate.isBlank()) {
            return "Ошибка: Не найден файл 'program.md' в 'assets/prompt/'."
        }

        val prompt = promptTemplate
            .replace("{name}", name)
            .replace("{score}", score)
            .replace("{grades}", grades.joinToString(", "))

        return ollamaRepository.generate(prompt, model).fold(
            onSuccess = {
                val cleanedResponse = it.trim().removeSurrounding("\"")
                programCache[name] = cleanedResponse
                cleanedResponse
            },
            onFailure = { "Ошибка: ${it.message}" }
        )
    }

    suspend fun getCourseComment(averageScore: Float, subjects: String): String {
        val cacheKey = "$averageScore-$subjects"
        val cachedComment = courseCommentCache[cacheKey]
        if (cachedComment != null) {
            return cachedComment
        }

        val promptTemplate = readPrompt("summary.md")
        if (promptTemplate.isBlank()) {
            return "Ошибка: Не найден файл 'summary.md' в 'assets/prompt/'."
        }

        val prompt = promptTemplate
            .replace("{subjects}", subjects)
            .replace("{averageScore}", String.format("%.2f", averageScore))

        return ollamaRepository.generate(prompt, model).fold(
            onSuccess = {
                val cleanedResponse = it.trim().removeSurrounding("\"")
                courseCommentCache[cacheKey] = cleanedResponse
                cleanedResponse
            },
            onFailure = { "Ошибка: ${it.message}" }
        )
    }
}