package com.uteke.kmm.android.view.screen.productlist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.uteke.kmm.android.view.screen.common.ErrorView
import com.uteke.kmm.android.view.screen.common.LoaderView
import com.uteke.kmm.feature.common.ErrorState
import com.uteke.kmm.feature.productlist.ProductListAction
import com.uteke.kmm.feature.productlist.ProductListViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest

@ExperimentalCoroutinesApi
@SuppressLint("FlowOperatorInvokedInComposition")
@Composable
fun ProductListScreen(
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

    Column {
        if (isProductsVisible) {
            ProductListView(
                productStates = productStates,
                onProductClick = { productId ->
                    navController.navigate("products/$productId")
                }
            )
        }

        if (isLoaderVisible) {
            LoaderView()
        }

        if (errorState.isVisible) {
            ErrorView(message = errorState.message)
        }
    }
}