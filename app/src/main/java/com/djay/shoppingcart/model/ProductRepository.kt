package com.djay.shoppingcart.model

import com.djay.shoppingcart.helpers.DataHelper

class ProductRepository : ProductDataSource {

    override fun retrieveProducts(): List<Product> {
        return DataHelper.createDummyProductList()
    }
}