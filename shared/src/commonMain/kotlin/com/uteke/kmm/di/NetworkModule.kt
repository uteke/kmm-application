package com.uteke.kmm.di

import com.uteke.kmm.network.ktor.HttpClientProvider
import com.uteke.kmm.network.ktor.product.KtorProductService
import com.uteke.kmm.network.product.ProductService
import org.koin.dsl.module

val networkModule = module {
    factory<ProductService> {
        KtorProductService(
            httpClient = HttpClientProvider().provide()
        )
    }
}