package com.pascal.imageai.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TextToImage(
    @SerialName("generationTime")
    val generationTime: Double? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("meta")
    val meta: Meta,
    @SerialName("nsfw_content_detected")
    val nsfwContentDetected: String? = null,
    @SerialName("output")
    val output: List<String>,
    @SerialName("proxy_links")
    val proxyLinks: List<String>,
    @SerialName("status")
    val status: String,
    @SerialName("tip")
    val tip: String
)

@Serializable
data class Meta(
    @SerialName("enable_attention_slicing")
    val enableAttentionSlicing: String,
    @SerialName("file_prefix")
    val filePrefix: String,
    @SerialName("guidance_scale")
    val guidanceScale: Double,
    @SerialName("H")
    val h: Int,
    @SerialName("instant_response")
    val instantResponse: String,
    @SerialName("model")
    val model: String,
    @SerialName("n_samples")
    val nSamples: Int,
    @SerialName("negative_prompt")
    val negativePrompt: String,
    @SerialName("outdir")
    val outdir: String,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("revision")
    val revision: String,
    @SerialName("safetychecker")
    val safetychecker: String,
    @SerialName("seed")
    val seed: Long,
    @SerialName("steps")
    val steps: Int,
    @SerialName("temp")
    val temp: String,
    @SerialName("vae")
    val vae: String,
    @SerialName("W")
    val w: Int
)