package com.uteke.kmm.android.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.uteke.kmm.feature.productitem.ProductItemAction
import com.uteke.kmm.feature.productitem.ProductItemState
import com.uteke.kmm.feature.productitem.ProductItemViewModel

@Composable
fun ProductItemScreen(
    modifier: Modifier,
    viewModel: ProductItemViewModel,
    productId: Int
) {
    LaunchedEffect(viewModel) {
        viewModel.handle(ProductItemAction.GetItem(productId = productId))
    }
    DisposableEffect(viewModel) {
        onDispose { viewModel.cancel() }
    }

    val viewState by viewModel.stateChanges.collectAsState()

    ProductItemContent(modifier = modifier, viewState = viewState)
}

@Composable
private fun ProductItemContent(
    modifier: Modifier,
    viewState: ProductItemState
) {
    with(viewState) {
        Column(modifier = modifier) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                items(count = images.size,
                    key = { index -> images[index] },
                    itemContent = { index ->
                        Image(
                            contentDescription = null,
                            modifier = Modifier.size(128.dp),
                            painter = rememberAsyncImagePainter(
                                ImageRequest.Builder(androidx.compose.ui.platform.LocalContext.current)
                                    .data(images[index])
                                    .crossfade(true)
                                    .transformations(CircleCropTransformation())
                                    .build()
                            ),
                        )
                    })
            }
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = title,
                style = MaterialTheme.typography.headlineLarge,
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = description,
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = kind,
                style = MaterialTheme.typography.titleSmall,
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = type,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = ingredients,
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = allergens,
                style = MaterialTheme.typography.bodyMedium,
            )

            if (isLoaderVisible) {
                LoaderView(modifier = Modifier.fillMaxSize())
            }

            if (errorState.isVisible) {
                ErrorView(
                    modifier = Modifier.fillMaxSize(),
                    message = errorState.message,
                )
            }
        }
    }
}

@Preview
@Composable
private fun ProductItemContentPreview() = MaterialTheme {
    ProductItemContent(
        modifier = Modifier,
        viewState = ProductItemState(
            isLoaderVisible = false,
            id = 1,
            title = "Burrata",
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
            kind = "Milk",
        )
    )
}