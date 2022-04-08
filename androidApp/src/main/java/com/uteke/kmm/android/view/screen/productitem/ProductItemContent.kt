package com.uteke.kmm.android.view.screen.productitem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun ProductItemContent(
    images: List<String>,
    title: String,
    description: String,
    kind: String,
    type: String,
    ingredients: String,
    allergens: String
) {
    Column {
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)) {
            items(
                count = images.size,
                key = { index -> images[index] },
                itemContent = { index ->
                    Image(
                        painter = rememberImagePainter(
                            data = images[index],
                            builder = {
                                crossfade(enable = true)
                                transformations(CircleCropTransformation())
                            }
                        ),
                        contentDescription = null,
                        modifier = Modifier.size(128.dp)
                    )
                }
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = description,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = kind,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = type,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = ingredients,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = allergens,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductItemContentPreview() {
    ProductItemContent(
        images = listOf("https://cdn.shopify.com/s/files/1/0832/9391/products/GOULIBEUR_CHOC.jpg?v=1529937995"),
        title = "title 1",
        description = "description 1",
        kind = "product",
        type = "test",
        ingredients = "tomatoes,carrots",
        allergens = ""
    )
}