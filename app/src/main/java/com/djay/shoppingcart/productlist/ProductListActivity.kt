package com.djay.shoppingcart.productlist

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.djay.shoppingcart.R
import com.djay.shoppingcart.ViewModelFactory
import com.djay.shoppingcart.di.Injection
import com.djay.shoppingcart.model.Product
import kotlinx.android.synthetic.main.activity_product_list.*
import kotlinx.android.synthetic.main.layout_error.*

class ProductListActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListAdapter

    companion object {
        const val TAG= "ProductListActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        setupViewModel()
        setupUI()
    }


    private fun setupUI() {
        adapter = ProductListAdapter(viewModel.museums.value ?: emptyList())
        rvProductList.layoutManager = GridLayoutManager(this,2)
        rvProductList.adapter = adapter
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(Injection.providerRepository()))
            .get(ProductListViewModel::class.java)
        viewModel.museums.observe(this, renderMuseums)

        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
        viewModel.onMessageError.observe(this, onMessageErrorObserver)
        viewModel.isEmptyList.observe(this, emptyListObserver)
    }

    //observers
    private val renderMuseums = Observer<List<Product>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility = View.GONE
        layoutNoItem.visibility = View.GONE
        adapter.update(it)
    }

    private val isViewLoadingObserver = Observer<Boolean> {
        Log.v(TAG, "isViewLoading $it")
        val visibility = if (it) View.VISIBLE else View.GONE
        progressBar.visibility = visibility
    }

    private val onMessageErrorObserver = Observer<Any> {
        Log.v(TAG, "onMessageError $it")
        layoutError.visibility = View.VISIBLE
        layoutNoItem.visibility = View.GONE
        tvError.text = "Error $it"
    }

    private val emptyListObserver = Observer<Boolean> {
        Log.v(TAG, "emptyListObserver $it")
        layoutNoItem.visibility = View.VISIBLE
        layoutError.visibility = View.GONE
    }


    //If you require updated data, you can call the method "loadMuseum" here
    override fun onResume() {
        super.onResume()
        viewModel.loadMuseums()
    }
}
