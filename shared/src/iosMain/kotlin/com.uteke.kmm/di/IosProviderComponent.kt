package com.uteke.kmm.di

import com.uteke.kmm.feature.productitem.ProductItemViewModel
import com.uteke.kmm.feature.productlist.ProductListViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

@Suppress("unused")
class IosProviderComponent: KoinComponent {
    fun provideProductListViewModel(): ProductListViewModel = get()
    fun provideProductItemViewModel(): ProductItemViewModel = get()
}