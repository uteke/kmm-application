package com.uteke.kmm.feature.productlist

import com.uteke.kmm.feature.common.Action

sealed class ProductListAction : Action {
    object GetList: ProductListAction()
}