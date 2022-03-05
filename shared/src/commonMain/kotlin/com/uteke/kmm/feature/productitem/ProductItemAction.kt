package com.uteke.kmm.feature.productitem

import com.uteke.kmm.feature.common.Action

sealed class ProductItemAction : Action {
    data class GetItem(val productId: Int): ProductItemAction()
}