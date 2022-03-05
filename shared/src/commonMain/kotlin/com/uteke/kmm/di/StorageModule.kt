package com.uteke.kmm.di

import com.uteke.kmm.storage.product.ProductDao
import com.uteke.kmm.storage.sqldelight.AppDatabase
import com.uteke.kmm.storage.sqldelight.ProductSqlDao
import org.koin.core.scope.Scope
import org.koin.dsl.module

expect fun Scope.createAppDatabase(): AppDatabase

val storageModule = module {
    factory<ProductDao> {
        ProductSqlDao(database = createAppDatabase())
    }
}