package com.djay.shoppingcart.model

interface ProductDataSource {

    fun retrieveProducts(): List<Product>
}