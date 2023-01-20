package com.uteke.kmm.android.view.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.uteke.kmm.feature.common.ErrorState
import com.uteke.kmm.feature.productlist.ProductListAction
import com.uteke.kmm.feature.productlist.ProductListViewModel
import com.uteke.kmm.feature.productlist.ProductState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
@SuppressLint("FlowOperatorInvokedInComposition")
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

    val productStates by viewModel.stateChanges
        .mapLatest { it.products }
        .distinctUntilChanged()
        .collectAsState(emptyList())

    val isProductsVisible by viewModel.stateChanges
        .mapLatest { it.isProductsVisible }
        .distinctUntilChanged()
        .collectAsState(false)

    val isLoaderVisible by viewModel.stateChanges
        .mapLatest { it.isLoaderVisible }
        .distinctUntilChanged()
        .collectAsState(false)

    val errorState by viewModel.stateChanges
        .mapLatest { it.errorState }
        .distinctUntilChanged()
        .collectAsState(ErrorState())

    Column(modifier = modifier) {
        if (isProductsVisible) {
            ProductListView(
                productStates = productStates,
                onProductClick = { productId ->
                    navController.navigate("products/$productId")
                }
            )
        }

        if (isLoaderVisible) {
            LoaderView(modifier = Modifier.fillMaxSize())
        }

        if (errorState.isVisible) {
            ErrorView(modifier = Modifier.fillMaxSize(), message = errorState.message)
        }
    }
}

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
fun ProductItemView(productState: ProductState, onClick: (id: Int) -> Unit) {
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
            Text(text = productState.title, style = typography.titleMedium)
            Text(text = productState.description, style = typography.bodyMedium)
            Text(text = productState.kind, style = typography.bodyMedium)
        }
    }
}