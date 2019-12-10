package com.djay.shoppingcart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.djay.shoppingcart.model.ProductDataSource
import com.djay.shoppingcart.productlist.ProductListViewModel

class ViewModelFactory(private val repository: ProductDataSource):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductListViewModel(repository) as T
    }
}