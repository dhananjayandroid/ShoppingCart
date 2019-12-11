package com.djay.shoppingcart.mycart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.djay.shoppingcart.model.Product
import com.djay.shoppingcart.model.ProductDataSource


class MyCartViewModel(private val repository: ProductDataSource) : ViewModel() {

    val products = MutableLiveData<List<Product>>().apply { value = emptyList() }

    init {
        loadProducts()
    }

    fun loadProducts() {
        products.value = repository.retrieveProducts()
    }
}