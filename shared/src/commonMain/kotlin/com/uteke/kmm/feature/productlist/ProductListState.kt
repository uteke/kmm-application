package com.uteke.kmm.feature.productlist

import com.uteke.kmm.feature.common.ErrorState
import com.uteke.kmm.feature.common.State

data class ProductListState(
    val products: List<ProductState> = emptyList(),
    val isProductsVisible: Boolean = false,
    val isLoaderVisible: Boolean = false,
    val errorState: ErrorState = ErrorState()
) : State