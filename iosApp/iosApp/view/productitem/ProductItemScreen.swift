//
//  ProductItemScreen.swift
//  iosApp
//
//  Created by Umut Teke on 11/02/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProductItemScreen: View {
    @ObservedObject private var viewModel: ProductItemObservableViewModel
    var productId: Int
    
    init(viewModel: ProductItemObservableViewModel, productId: Int) {
        self.viewModel = viewModel
        self.productId = productId
    }
    
    var body: some View {
        VStack {
            ScrollView {
                LazyHStack {
                    ForEach(viewModel.state.images, id: \.self) { image in
                        AsyncImage(url: URL(string: image)) { image in
                            image.cornerRadius(20)
                        } placeholder: {
                            ProgressView()
                                .frame(width: 100, height: 100, alignment: .center)
                        }
                        .frame(minWidth: 100, maxWidth: .infinity, maxHeight: 50)
                            .background(Color.primary)
                            .padding(10)
                    }
                }
            }
            
            Text(viewModel.state.title)
                .font(.title)
                .bold()
            
            Text(viewModel.state.description_)
                .font(.body)
                .bold()
            
            Text(viewModel.state.kind)
                .font(.body)
    
            Text(viewModel.state.type)
                .font(.body)
            
            Text(viewModel.state.ingredients)
                .font(.body)
            
            
            Text(viewModel.state.allergens)
                .font(.body)
        }
        .onAppear(perform: {
            viewModel.observe(productId: productId)
        })
        .onDisappear(perform: {
            viewModel.cancel()
        })
    }
}

