package com.uteke.kmm.android.view.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.uteke.kmm.feature.productlist.ProductListAction
import com.uteke.kmm.feature.productlist.ProductListViewModel
import com.uteke.kmm.feature.productlist.ProductState

@Composable
fun ProductListScreen(
    modifier: Modifier,
    viewModel: ProductListViewModel,
    navController: NavController
) {
    LaunchedEffect(viewModel) {
        viewModel.handle(ProductListAction.GetList)
    }
    DisposableEffect(viewModel) {
        onDispose { viewModel.cancel() }
    }

    val viewState by viewModel.stateChanges.collectAsState()

    with(viewState) {
        if (isProductsVisible) {
            ProductListContent(
                modifier = modifier,
                productStates = viewState.products,
                onProductClick = { productId ->
                    navController.navigate("products/$productId")
                },
            )
        }

        if (isLoaderVisible) {
            LoaderView(modifier = Modifier.fillMaxSize())
        }

        if (errorState.isVisible) {
            ErrorView(
                modifier = Modifier.fillMaxSize(),
                message = viewState.errorState.message,
            )
        }
    }
}

@Composable
private fun ProductListContent(
    modifier: Modifier,
    productStates: List<ProductState>,
    onProductClick: (productId: Int) -> Unit
) {
    LazyColumn(
        modifier = modifier,
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
private fun ProductItemView(productState: ProductState, onClick: (id: Int) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onClick(productState.id) })
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            contentDescription = null,
            modifier = Modifier.size(128.dp),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current)
                    .data(productState.imageUrl)
                    .crossfade(true)
                    .transformations(CircleCropTransformation())
                    .build()
            ),
        )

        Column(
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = productState.title, style = typography.titleMedium)
            Text(text = productState.description, style = typography.bodyMedium)
            Text(text = productState.kind, style = typography.bodyMedium)
        }
    }
}

@Preview
@Composable
private fun ProductListContentPreview() = MaterialTheme {
    ProductListContent(
        modifier = Modifier,
        productStates = listOf(
            ProductState(
                id = 1,
                title = "Burrata",
                description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
            ),
            ProductState(
                id = 2,
                title = "Cookie",
                description = "Consequat nisl vel pretium lectus quam id leo in. Mauris in aliquam sem fringilla ut morbi. Eget nunc scelerisque viverra mauris in aliquam. At imperdiet dui accumsan sit amet nulla. Viverra accumsan in nisl nisi",
            )
        ),
        onProductClick = {},
    )
}