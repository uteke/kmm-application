package com.uteke.kmm.network.ktor.product

import com.uteke.kmm.network.product.ProductRemoteModel
import com.uteke.kmm.network.product.ProductService
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class KtorProductService(
    private val httpClient: HttpClient
) : ProductService {
    override fun getProducts(): Flow<List<ProductRemoteModel>> =
        flow {
            val products = httpClient.get<List<ProductJsonModel>>(ENDPOINT)
                .map { it.toProductRemoteModel() }
            emit(products)
        }

    private fun ProductJsonModel.toProductRemoteModel() =
        ProductRemoteModel(
            id = productId,
            title = title,
            description = description,
            kind = kind,
            type = type,
            price = price,
            ingredients = ingredients,
            allergens = allergens,
            images = images
        )

    private companion object {
        const val ENDPOINT = "https://frichti-mobile.s3-eu-west-1.amazonaws.com/mobile-technical-test.json"
    }
}