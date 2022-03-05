package com.uteke.kmm.network.product

import kotlinx.coroutines.flow.Flow

interface ProductService {
    fun getProducts(): Flow<List<ProductRemoteModel>>
}