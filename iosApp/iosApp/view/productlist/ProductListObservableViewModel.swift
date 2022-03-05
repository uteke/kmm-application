//
//  ProductListObservableViewModel.swift
//  iosApp
//
//  Created by Umut Teke on 11/02/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import Combine

class ProductListObservableViewModel : ObservableObject {
    @Published var state: ProductListState = ProductListState(
        products: Array<ProductState>(),
        isProductsVisible: false,
        isLoaderVisible: false,
        errorState: ErrorState(message: "", isVisible: false)
    )
    private var viewModel: ProductListViewModel

    init(viewModel: ProductListViewModel) {
        self.viewModel = viewModel

        viewModel.collect(flow: viewModel.stateChanges, collector: { state in
            self.state = state as! ProductListState
        })
    }
    
    func observe() {
        viewModel.handle(action: ProductListAction.GetList())
    }
    
    func cancel() {
        viewModel.cancel()
    }
}
