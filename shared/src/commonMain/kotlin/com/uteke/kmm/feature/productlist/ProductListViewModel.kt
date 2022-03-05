package com.uteke.kmm.feature.productlist

import com.uteke.kmm.feature.common.*
import kotlinx.coroutines.flow.onStart

open class ProductListViewModel(
    initialState: ProductListState = ProductListState(),
    schedulerProvider: SchedulerProvider,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel<ProductListState, ProductListEvent, ProductListAction>(initialState, schedulerProvider) {
    override fun handle(action: ProductListAction) {
        when (action) {
            is ProductListAction.GetList -> getList()
        }
    }

    private fun getList() {
        getProductsUseCase()
            .onStart {
                transform { previousState ->
                    previousState.copy(
                        isLoaderVisible = true,
                        isProductsVisible = false
                    )
                }
            }
            .launch(
                onSuccess = { products ->
                    transform { previousState ->
                        previousState.copy(
                            products = products.map {
                                ProductState(
                                    id = it.id,
                                    title = it.title,
                                    description = it.description,
                                    kind = it.kind,
                                    imageUrl = it.image ?: "",
                                    isImageVisible = it.image.isNullOrEmpty().not()
                                )
                            },
                            isProductsVisible = true,
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


