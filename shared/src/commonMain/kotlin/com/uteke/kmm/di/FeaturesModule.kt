package com.uteke.kmm.di

import com.uteke.kmm.feature.productitem.GetProductCachedUseCase
import com.uteke.kmm.feature.productitem.GetProductUseCase
import com.uteke.kmm.feature.productitem.ProductItemViewModel
import com.uteke.kmm.feature.productlist.GetProductsFetchUseCase
import com.uteke.kmm.feature.productlist.GetProductsUseCase
import com.uteke.kmm.feature.productlist.ProductListViewModel
import kotlinx.coroutines.FlowPreview
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val commonModule: Module

@FlowPreview
val productListModule = module {
    factory {
        ProductListViewModel(
            schedulerProvider = get(),
            getProductsUseCase = GetProductsFetchUseCase(
                productDao = get(),
                productService = get()
            )
        )
    }

    factoryOf(::GetProductsFetchUseCase) bind GetProductsUseCase::class
}

val productItemModule = module {
    factory {
        ProductItemViewModel(
            schedulerProvider = get(),
            getProductUseCase = GetProductCachedUseCase(
                productDao = get()
            )
        )
    }

    factoryOf(::GetProductCachedUseCase) bind GetProductUseCase::class
}