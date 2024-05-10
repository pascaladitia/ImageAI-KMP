package com.pascal.imageai.data.remote

import com.pascal.imageai.BuildKonfig
import com.pascal.imageai.domain.model.TextToImage
import com.pascal.imageai.utils.Constant.API_KEY
import com.pascal.imageai.utils.Constant.TIMEOUT
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.koin.core.annotation.Single

@Single
object KtorClientApi {
    @OptIn(ExperimentalSerializationApi::class)
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
                explicitNulls = false
            })
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    co.touchlab.kermit.Logger.d(tag = "KtorClient", null) {
                        message
                    }
                }
            }
        }

        defaultRequest {
            header("Content-Type", "application/json")
        }

        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
    }

    @OptIn(ExperimentalSerializationApi::class)
    suspend fun textToImage(text: String): TextToImage {
        val body = buildJsonObject {
            put("key", API_KEY)
            put("prompt", text)
            put(
                "negative_prompt",
                "((out of frame)), ((extra fingers)), mutated hands, ((poorly drawn hands)), ((poorly drawn face)), (((mutation))), (((deformed))), (((tiling))), ((naked)), ((tile)), ((fleshpile)), ((ugly)), (((abstract))), blurry, ((bad anatomy)), ((bad proportions)), ((extra limbs)), cloned face, (((skinny))), glitchy, ((extra breasts)), ((double torso)), ((extra arms)), ((extra hands)), ((mangled fingers)), ((missing breasts)), (missing lips), ((ugly face)), ((fat)), ((extra legs))"
            )
            put("width", "512")
            put("height", "512")
            put("samples", "1")
            put("num_inference_steps", "20")
            put("safety_checker", "no")
            put("enhance_prompt", "yes")
            put("temp", "yes")
            put("seed", null)
            put("guidance_scale", 7.5)
            put("webhook", null)
            put("track_id", null)
        }

        return client.post("${BuildKonfig.BASE_URL}/text2img") {
            setBody(body)
        }.body()
    }
}