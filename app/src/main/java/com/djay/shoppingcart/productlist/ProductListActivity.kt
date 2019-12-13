package com.djay.shoppingcart.productlist

import android.content.Intent
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
import com.djay.shoppingcart.productdetails.ProductDetailsActivity
import kotlinx.android.synthetic.main.activity_product_list.*

class ProductListActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductListViewModel
    private lateinit var adapter: ProductListAdapter

    companion object {
        const val TAG = "ProductListActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)

        setupViewModel()
        setupUI()
    }

    private fun setupUI() {
        adapter = ProductListAdapter(emptyList())
        adapter.onItemClick = { product ->
            val intent = Intent(this, ProductDetailsActivity::class.java)
            intent.putExtra(ProductDetailsActivity.ARG_PRODUCT, product)
            startActivity(intent)
        }

        rvProductList.apply {
            adapter = this@ProductListActivity.adapter
            rvProductList.layoutManager = GridLayoutManager(this@ProductListActivity, 2)
            rvProductList.adapter = adapter
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, ViewModelFactory(Injection.providerRepository()))
            .get(ProductListViewModel::class.java)
        viewModel.products.observe(this, renderProducts)
    }

    private val renderProducts = Observer<List<Product>> {
        Log.v(TAG, "data updated $it")
        layoutError.visibility = View.GONE
        layoutNoItem.visibility = View.GONE
        adapter.update(it)
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadProducts()
    }
}
