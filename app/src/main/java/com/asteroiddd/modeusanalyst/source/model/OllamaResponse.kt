package com.asteroiddd.modeusanalyst.source.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OllamaResponse(
    @SerialName("model")                val model: String? = null,
    @SerialName("created_at")           val createdAt: String? = null,
    @SerialName("response")             val response: String,
    @SerialName("done")                 val done: Boolean? = null,
    @SerialName("done_reason")          val doneReason: String? = null,
    @SerialName("context")              val context: List<Int>? = null,
    @SerialName("total_duration")       val totalDuration: Long? = null,
    @SerialName("load_duration")        val loadDuration: Long? = null,
    @SerialName("prompt_eval_count")    val promptEvalCount: Int? = null,
    @SerialName("prompt_eval_duration") val promptEvalDuration: Long? = null,
    @SerialName("eval_count")           val evalCount: Int? = null,
    @SerialName("eval_duration")        val evalDuration: Long? = null
)

@Serializable
data class OllamaSimpleResponse(
    val response: String
)