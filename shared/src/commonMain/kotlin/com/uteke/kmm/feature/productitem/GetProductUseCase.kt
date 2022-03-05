package com.uteke.kmm.feature.productitem

import kotlinx.coroutines.flow.Flow

interface GetProductUseCase {
    operator fun invoke(productId: Int): Flow<ProductModel>
}

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val kind: String,
    val type: String,
    val images: List<String>,
    val ingredients: List<String>,
    val allergens: List<String>
)

class GetProductException(
    message: String? = null,
    cause: Throwable? = null
) : Throwable(message, cause)