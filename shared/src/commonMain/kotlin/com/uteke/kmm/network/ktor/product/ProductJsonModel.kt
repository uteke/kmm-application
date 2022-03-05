package com.uteke.kmm.network.ktor.product

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductJsonModel(
    @SerialName("productId")
    val productId: Int?,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("kind")
    val kind: String,
    @SerialName("type")
    val type: String,
    @SerialName("price")
    val price: Double,
    @SerialName("ingredients")
    val ingredients: List<String>,
    @SerialName("allergens")
    val allergens: List<String>,
    @SerialName("images")
    val images: List<String>
)