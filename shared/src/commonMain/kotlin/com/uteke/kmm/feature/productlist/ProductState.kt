package com.uteke.kmm.feature.productlist

data class ProductState(
    val id: Int = 0,
    val title: String = "",
    val description: String = "",
    val kind: String = "",
    val imageUrl: String = "",
    val isImageVisible: Boolean = false
)