package com.uteke.kmm.storage.product

data class ProductLocalModel(
    val id: Int,
    val title: String,
    val description: String,
    val kind: String,
    val type: String,
    val price: Int,
    val ingredients: List<String>,
    val allergens: List<String>,
    val images: List<String>
)