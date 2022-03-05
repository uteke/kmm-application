//
//  ProductListScreen.swift
//  iosApp
//
//  Created by Umut Teke on 10/02/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProductListScreen: View {
    @ObservedObject private var viewModel: ProductListObservableViewModel
    let component: IosProviderComponent = IosProviderComponent()

    init(viewModel: ProductListObservableViewModel) {
        self.viewModel = viewModel
    }
    
    var body: some View {
        NavigationView {
            ScrollView {
                LazyVStack{
                    ForEach(viewModel.state.products, id: \.self) { product in
                        NavigationLink(
                            destination: ProductItemScreen(
                                viewModel: ProductItemObservableViewModel(
                                    viewModel: component.provideProductItemViewModel()
                                ),
                                productId: Int(product.id)
                            )
                        ) { ProductItemView(product: product) }.buttonStyle(PlainButtonStyle())
                        
                        Divider()
                    }
                }
            }
            .onAppear(perform: {
               viewModel.observe()
            })
            .onDisappear(perform: {
               viewModel.cancel()
                
            })
        }
    }
}
