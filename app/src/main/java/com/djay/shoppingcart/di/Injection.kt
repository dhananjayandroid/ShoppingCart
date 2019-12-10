package com.djay.shoppingcart.di

import com.djay.shoppingcart.model.ProductDataSource
import com.djay.shoppingcart.model.ProductRepository

object Injection {

    fun providerRepository(): ProductDataSource {
        return ProductRepository()
    }
}