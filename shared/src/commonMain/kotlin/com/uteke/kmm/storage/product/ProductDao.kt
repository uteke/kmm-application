package com.uteke.kmm.storage.product

interface ProductDao {
    suspend fun selectAll(): List<ProductLocalModel>
    suspend fun selectById(id: Int): ProductLocalModel
    suspend fun insert(product: ProductLocalModel)
    suspend fun deleteAll()
}