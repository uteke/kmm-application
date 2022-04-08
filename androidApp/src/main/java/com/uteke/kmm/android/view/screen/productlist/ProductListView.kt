package com.uteke.kmm.android.view.screen.productlist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.uteke.kmm.feature.productlist.ProductState

@Composable
fun ProductListView(
    productStates: List<ProductState>,
    onProductClick: (productId: Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            count = productStates.size,
            key = { index -> productStates[index].id },
            itemContent = { index ->
                ProductItemView(
                    productState = productStates[index],
                    onClick = onProductClick
                )
                Divider(color = Color.Black)
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProductListViewPreview() {
    ProductListView(
        productStates = listOf(
            ProductState(
                id = 1,
                title = "Product title 1",
                description = "Product description 1",
                kind = "product",
                imageUrl = "https://cdn.shopify.com/s/files/1/0832/9391/products/GOULIBEUR_CHOC.jpg?v=1529937995",
                isImageVisible = true
            ),
            ProductState(
                id = 2,
                title = "Product title 2",
                description = "Product description 2",
                kind = "product",
                imageUrl = "",
                isImageVisible = false
            )
        ),
        onProductClick = {}
    )
}