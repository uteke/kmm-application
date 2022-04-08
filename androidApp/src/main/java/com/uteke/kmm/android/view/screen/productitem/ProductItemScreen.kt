package com.uteke.kmm.android.view.screen.productitem

import android.annotation.SuppressLint
import androidx.compose.runtime.*
import com.uteke.kmm.android.view.screen.common.ErrorView
import com.uteke.kmm.android.view.screen.common.LoaderView
import com.uteke.kmm.feature.common.ErrorState
import com.uteke.kmm.feature.productitem.ProductItemAction
import com.uteke.kmm.feature.productitem.ProductItemViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.mapLatest

@SuppressLint("FlowOperatorInvokedInComposition")
@ExperimentalCoroutinesApi
@Composable
fun ProductItemScreen(
    viewModel: ProductItemViewModel,
    productId: Int
) {
    LaunchedEffect(viewModel) {
        viewModel.handle(ProductItemAction.GetItem(productId = productId))
    }
    DisposableEffect(viewModel) {
        onDispose { viewModel.cancel() }
    }

    val isLoaderVisible by viewModel.stateChanges
        .mapLatest { it.isLoaderVisible }
        .distinctUntilChanged()
        .collectAsState(false)

    val isProductVisible by viewModel.stateChanges
        .mapLatest { it.isProductVisible }
        .distinctUntilChanged()
        .collectAsState(false)

    val errorState by viewModel.stateChanges
        .mapLatest { it.errorState }
        .distinctUntilChanged()
        .collectAsState(ErrorState())

    val images by viewModel.stateChanges
        .mapLatest { it.images }
        .distinctUntilChanged()
        .collectAsState(emptyList())

    val title by viewModel.stateChanges
        .mapLatest { it.title }
        .distinctUntilChanged()
        .collectAsState("")

    val description by viewModel.stateChanges
        .mapLatest { it.description }
        .distinctUntilChanged()
        .collectAsState("")

    val kind by viewModel.stateChanges
        .mapLatest { it.kind }
        .distinctUntilChanged()
        .collectAsState("")

    val type by viewModel.stateChanges
        .mapLatest { it.type }
        .distinctUntilChanged()
        .collectAsState("")

    val ingredients by viewModel.stateChanges
        .mapLatest { it.ingredients }
        .distinctUntilChanged()
        .collectAsState("")

    val allergens by viewModel.stateChanges
        .mapLatest { it.allergens }
        .distinctUntilChanged()
        .collectAsState("")

    if (isProductVisible) {
        ProductItemContent(
            images = images,
            title = title,
            description = description,
            kind = kind,
            type = type,
            ingredients = ingredients,
            allergens = allergens
        )
    }

    if (isLoaderVisible) {
        LoaderView()
    }

    if (errorState.isVisible) {
        ErrorView(message = errorState.message)
    }
}