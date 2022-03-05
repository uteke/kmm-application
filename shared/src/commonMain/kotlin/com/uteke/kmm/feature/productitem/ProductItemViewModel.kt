package com.uteke.kmm.feature.productitem

import com.uteke.kmm.feature.common.*
import kotlinx.coroutines.flow.onStart

open class ProductItemViewModel(
    initialState: ProductItemState = ProductItemState(),
    schedulerProvider: SchedulerProvider,
    private val getProductUseCase: GetProductUseCase
) : ViewModel<ProductItemState, ProductItemEvent, ProductItemAction>(initialState, schedulerProvider) {
    override fun handle(action: ProductItemAction) {
        when (action) {
            is ProductItemAction.GetItem -> getItem(productId = action.productId)
        }
    }

    private fun getItem(productId: Int) {
        getProductUseCase(productId = productId)
            .onStart {
                transform { previousState ->
                    previousState.copy(
                        isLoaderVisible = true
                    )
                }
            }
            .launch(
                onSuccess = { product ->
                    transform { previousState ->
                        previousState.copy(
                            id = product.id,
                            title = product.title,
                            description = product.description,
                            kind = product.kind,
                            type = product.type,
                            ingredients = product.ingredients.joinToString(),
                            allergens = product.allergens.joinToString(),
                            images = product.images,
                            isLoaderVisible = false,
                            errorState = ErrorState()
                        )
                    }
                },
                onError = { throwable ->
                    transform { previousState ->
                        previousState.copy(
                            isLoaderVisible = false,
                            errorState = ErrorState(
                                message = throwable.message ?: "ERROR",
                                isVisible = true
                            )
                        )
                    }
                }
            )
    }
}