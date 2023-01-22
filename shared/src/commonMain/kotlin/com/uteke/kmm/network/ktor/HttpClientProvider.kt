package com.uteke.kmm.network.ktor

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class HttpClientProvider {
    fun provide(): HttpClient = HttpClient {
        install(HttpTimeout) {
            connectTimeoutMillis = TIMEOUT_IN_MILLIS
            requestTimeoutMillis = TIMEOUT_IN_MILLIS
        }
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            level = LogLevel.ALL
        }
    }

    private companion object {
        const val TIMEOUT_IN_MILLIS = 30_000L
    }
}