package com.uteke.kmm.network.ktor

import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import kotlinx.serialization.json.Json


class HttpClientProvider {
    fun provide(): HttpClient = HttpClient {
        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT_IN_MILLIS
            requestTimeoutMillis = TIMEOUT_IN_MILLIS
        }
        install(JsonFeature) {
            val json = Json {
                ignoreUnknownKeys = true
            }
            serializer = KotlinxSerializer(json)
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    private companion object {
        const val TIMEOUT_IN_MILLIS = 30_000L
    }
}