package com.pascal.imageai.data.remote

import com.pascal.imageai.BuildKonfig
import com.pascal.imageai.domain.model.ImageResponse
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
            header("content-type", "application/json")
            header("authorization", "Bearer $API_KEY")
        }

        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT
            requestTimeoutMillis = TIMEOUT
            socketTimeoutMillis = TIMEOUT
        }
    }

    suspend fun textToImage(text: String): ImageResponse {
        val body = """
            {
                "response_as_dict":true,
                "attributes_as_list":false,
                "show_original_response":false,
                "num_images":2,
                "providers":"deepai,stabilityai,openai,replicate,amazon",
                "text":"$text",
                "resolution":"512x512"
            }
        """.trimIndent()

        return client.post("${BuildKonfig.BASE_URL}/image/generation") {
            setBody(body)
        }.body()
    }
}