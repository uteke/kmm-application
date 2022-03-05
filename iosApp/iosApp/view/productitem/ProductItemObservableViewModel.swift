//
//  ProductItemObservableViewModel.swift
//  iosApp
//
//  Created by Umut Teke on 11/02/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import shared
import Combine

class ProductItemObservableViewModel : ObservableObject {
    @Published var state: ProductItemState = ProductItemState(
        isLoaderVisible: false,
        id: 0,
        title: "",
        description: "",
        kind: "", type: "",
        ingredients: "",
        allergens: "",
        images: Array<String>(),
        errorState: ErrorState(message: "", isVisible: false)
    )
    private var viewModel: ProductItemViewModel

    init(viewModel: ProductItemViewModel) {
        self.viewModel = viewModel

        viewModel.collect(flow: viewModel.stateChanges, collector: { state in
            self.state = state as! ProductItemState
        })
    }
    
    func observe(productId: Int) {
        viewModel.handle(action: ProductItemAction.GetItem(productId: Int32(productId)))
    }
    
    func cancel() {
        viewModel.cancel()
    }
}
