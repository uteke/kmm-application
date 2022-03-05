//
//  ProductItemView.swift
//  iosApp
//
//  Created by Umut Teke on 13/02/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct ProductItemView: View {
    var product: ProductState
    
    var body: some View {
        HStack {
            AsyncImage(url: URL(string: product.imageUrl)) { image in
                image
                    .resizable()
                    .cornerRadius(20)
                    .frame(width: 100, height: 100, alignment: .center)
            } placeholder: {
                ProgressView()
                    .frame(width: 100, height: 100, alignment: .center)
            }
            VStack(alignment: .leading) {
                Text(product.title)
                    .font(Font.title2)
                    .bold()
                    .padding(.bottom, 10)
                    .padding(.top, 5)
                Text(product.description_)
                    .multilineTextAlignment(.leading)
                Text(product.kind)
            }
        }
    }
}

struct ProductItemView_Previews: PreviewProvider {
    static var previews: some View {
        ProductItemView(
            product: ProductState(
                id: 1,
                title: "product title",
                description: "product description",
                kind: "product",
                imageUrl: "",
                isImageVisible: true
            )
        ).previewLayout(.sizeThatFits)
    }
}
