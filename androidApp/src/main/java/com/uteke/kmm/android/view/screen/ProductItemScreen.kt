package com.uteke.kmm.android.view.feature

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.uteke.kmm.android.view.screen.ErrorView
import com.uteke.kmm.android.view.screen.LoaderView
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

        if (isLoaderVisible) {
            LoaderView()
        }

        if (errorState.isVisible) {
            ErrorView(message = errorState.message)
        }
    }
}