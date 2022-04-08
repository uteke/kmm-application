package com.uteke.kmm.android.view.screen.productlist

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.uteke.kmm.feature.productlist.ProductState


@Composable
fun ProductItemView(
    productState: ProductState,
    onClick: (id: Int) -> Unit
) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onClick(productState.id) })
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = rememberImagePainter(
                data = productState.imageUrl,
                builder = {
                    crossfade(enable = true)
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )

        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = productState.title, style = MaterialTheme.typography.titleMedium)
            Text(text = productState.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = productState.kind, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ProductItemViewPreview() {
    ProductItemView(
        productState = ProductState(
            id = 1,
            title = "Product title 1",
            description = "Product description 1",
            kind = "product",
            imageUrl = "https://cdn.shopify.com/s/files/1/0832/9391/products/GOULIBEUR_CHOC.jpg?v=1529937995",
            isImageVisible = true
        ),
        onClick = {}
    )
}