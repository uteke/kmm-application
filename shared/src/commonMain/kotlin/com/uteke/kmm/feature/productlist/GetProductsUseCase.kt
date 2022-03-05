package com.uteke.kmm.feature.productlist

import kotlinx.coroutines.flow.Flow

interface GetProductsUseCase {
    operator fun invoke(): Flow<List<ProductModel>>
}

data class ProductModel(
    val id: Int,
    val title: String,
    val description: String,
    val kind: String,
    val image: String?
)

class GetProductsException(
    message: String? = null,
    cause: Throwable? = null
) : Throwable(message, cause)