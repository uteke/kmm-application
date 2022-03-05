package com.uteke.kmm.feature.productitem

import com.uteke.kmm.feature.common.ErrorState
import com.uteke.kmm.feature.common.State

data class ProductItemState(
    val isLoaderVisible: Boolean = false,
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val kind: String = "",
    val type: String = "",
    val ingredients: String = "",
    val allergens: String = "",
    val images: List<String> = emptyList(),
    val errorState: ErrorState = ErrorState()
) : State