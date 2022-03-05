package com.uteke.kmm.feature.productlist

import com.uteke.kmm.network.product.ProductRemoteModel
import com.uteke.kmm.network.product.ProductService
import com.uteke.kmm.storage.product.ProductDao
import com.uteke.kmm.storage.product.ProductLocalModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*

@FlowPreview
class GetProductsFetchUseCase(
    private val productService: ProductService,
    private val productDao: ProductDao
) : GetProductsUseCase {
    override fun invoke(): Flow<List<ProductModel>> =
        productService.getProducts()
            .map { remoteProducts -> remoteProducts.filter { it.id != null  } }
            .onEach { remoteProducts ->
                remoteProducts.forEach {  productDao.insert(it.toProductLocalModel()) }
            }
            .flatMapConcat { flowOf(productDao.selectAll().map { it.toProductModel() }) }
            .catch { e ->
                throw GetProductsException(
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
            image = images.getOrNull(0)
        )

    private fun ProductRemoteModel.toProductLocalModel() =
        ProductLocalModel(
            id = id ?: 0,
            title = title,
            description = description,
            kind = kind,
            type = type,
            price = price.toInt(),
            ingredients = ingredients,
            allergens = allergens,
            images = images
        )
}