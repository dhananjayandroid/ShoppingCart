package com.djay.shoppingcart.productlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.djay.shoppingcart.model.Product
import com.djay.shoppingcart.model.ProductDataSource

class ProductListViewModel(private val repository: ProductDataSource):ViewModel() {

    private val _museums = MutableLiveData<List<Product>>().apply { value = emptyList() }
    val museums: LiveData<List<Product>> = _museums

    private val _isViewLoading=MutableLiveData<Boolean>()
    val isViewLoading:LiveData<Boolean> = _isViewLoading

    private val _onMessageError=MutableLiveData<Any>()
    val onMessageError:LiveData<Any> = _onMessageError

    private val _isEmptyList=MutableLiveData<Boolean>()
    val isEmptyList:LiveData<Boolean> = _isEmptyList

    init {
        loadMuseums()
    }

    fun loadMuseums(){
        _museums.value = repository.retrieveProducts() as List<Product>
    }
}