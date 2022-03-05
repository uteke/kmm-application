package com.uteke.kmm.feature.productitem

import com.uteke.kmm.storage.product.ProductDao
import com.uteke.kmm.storage.product.ProductLocalModel
import kotlinx.coroutines.flow.*

class GetProductCachedUseCase(
    private val productDao: ProductDao
) : GetProductUseCase {
    override fun invoke(productId: Int): Flow<ProductModel> = flow {
        emit(productDao.selectById(id = productId).toProductModel())
    }.catch { e ->
        throw GetProductException(
            message = e.message,
            cause = e
        )
    }

    private fun ProductLocalModel.toProductModel() =
        ProductModel(
            id = id,
            title = title,
            description = description,
            kind = kind,
            type = type,
            images = images,
            ingredients = ingredients,
            allergens = allergens
        )
}