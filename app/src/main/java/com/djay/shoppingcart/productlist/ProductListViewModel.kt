package com.djay.shoppingcart.productlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.djay.shoppingcart.model.Product
import com.djay.shoppingcart.model.ProductDataSource

class ProductListViewModel(private val repository: ProductDataSource) : ViewModel() {

    val products = MutableLiveData<List<Product>>().apply { value = emptyList() }

    fun loadProducts() {
        products.value = repository.retrieveProducts()
    }
}