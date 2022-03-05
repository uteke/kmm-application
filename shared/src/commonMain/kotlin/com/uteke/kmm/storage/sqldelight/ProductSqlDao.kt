package com.uteke.kmm.storage.sqldelight

import com.uteke.kmm.storage.product.ProductDao
import com.uteke.kmm.storage.product.ProductLocalModel

class ProductSqlDao(database: AppDatabase): ProductDao {
    private val dbQuery = database.appDatabaseQueries

    override suspend fun selectAll(): List<ProductLocalModel> =
        dbQuery.selectAll()
            .executeAsList()
            .map { it.toProductLocalModel() }

    override suspend fun selectById(id: Int): ProductLocalModel =
        dbQuery.selectById(id = id.toLong())
            .executeAsOne()
            .toProductLocalModel()

    override suspend fun insert(product: ProductLocalModel) {
        dbQuery.insertProduct(
            id = product.id.toLong(),
            title = product.title,
            description = product.description,
            kind = product.kind,
            type = product.type,
            price = product.price.toLong(),
            ingredients = product.ingredients.join(),
            allergens = product.allergens.join(),
            images = product.images.join()
        )
    }

    override suspend fun deleteAll() {
        dbQuery.deleteAll()
    }

    private fun Product.toProductLocalModel() =
        ProductLocalModel(
            id = id.toInt(),
            title = title,
            description = description,
            kind = kind,
            type = type,
            price = price.toInt(),
            ingredients = ingredients.split(),
            allergens = allergens.split(),
            images = images.split()
        )

    private fun List<String>.join(): String = joinToString(separator = DELIMITER)

    private fun String?.split(): List<String> = this?.split(DELIMITER) ?: emptyList()

    private companion object {
        const val DELIMITER = ";"
    }
}