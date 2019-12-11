package com.djay.shoppingcart.mycart

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.djay.shoppingcart.R
import com.djay.shoppingcart.ViewModelFactory
import com.djay.shoppingcart.di.Injection
import com.djay.shoppingcart.model.Product
import com.djay.shoppingcart.productdetails.ProductDetailsActivity
import com.djay.shoppingcart.productlist.ProductListAdapter
import com.djay.shoppingcart.productlist.ProductListViewModel
import kotlinx.android.synthetic.main.activity_my_cart.*


class MyCartActivity : AppCompatActivity() {

    private lateinit var viewModel: MyCartViewModel
    private lateinit var adapter: MyCartAdapter

    companion object {
        const val TAG= "MyCartActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_my_cart)
        setupViewModel()
        setupUI()
    }

    private fun setupUI() {
        adapter = MyCartAdapter(/*viewModel.products.value ?: */emptyList())
        rvCart.layoutManager = LinearLayoutManager(this)
        rvCart.adapter = adapter
    }

    private fun setupViewModel() {
//        viewModel = ViewModelProviders.of(this, ViewModelFactory(Injection.providerRepository()))
//            .get(ProductListViewModel::class.java)
//        viewModel.products.observe(this, renderProducts)
    }

    private val renderProducts = Observer<List<Product>> {
        Log.v(TAG, "data updated $it")
        adapter.update(it)
    }

    override fun onResume() {
        super.onResume()
//        viewModel.loadProducts()
    }
}
