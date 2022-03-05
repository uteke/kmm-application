package com.uteke.kmm.network.product

data class ProductRemoteModel(
    val id: Int?,
    val title: String,
    val description: String,
    val kind: String,
    val type: String,
    val price: Double,
    val ingredients: List<String>,
    val allergens: List<String>,
    val images: List<String>
)