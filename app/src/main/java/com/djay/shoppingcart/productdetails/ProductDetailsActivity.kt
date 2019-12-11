package com.djay.shoppingcart.productdetails

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.djay.shoppingcart.R
import com.djay.shoppingcart.mycart.MyCartActivity

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: ProductDetailsViewModel

    companion object {
        const val PRODUCT = "Product"
        const val TAG = "ProductDetailsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_product_details)
//        setupViewModel()
//        setupUI()
    }

    fun goTOCart(view : View) {
        val intent = Intent(this, MyCartActivity::class.java)
        startActivity(intent)
        finish()
    }
//
//    private fun setupUI() {
//        adapter = ProductListAdapter(viewModel.museums.value ?: emptyList())
//        rvProductList.layoutManager = GridLayoutManager(this, 2)
//        rvProductList.adapter = adapter
//        adapter.onItemClick = { product ->
//            val intent = Intent(this, ProductDetailsActivity::class.java)
//            intent.putExtra(ItemDetailViewModel.Companion.ARG_ITEM, movie)
//        }
//    }
//
//    private fun setupViewModel() {
//        viewModel = ViewModelProviders.of(this, ViewModelFactory(Injection.providerRepository()))
//            .get(ProductDetailsViewModel::class.java)
//        viewModel.museums.observe(this, renderMuseums)
//
//        viewModel.isViewLoading.observe(this, isViewLoadingObserver)
//        viewModel.onMessageError.observe(this, onMessageErrorObserver)
//        viewModel.isEmptyList.observe(this, emptyListObserver)
//    }
//
//    //observers
//    private val renderMuseums = Observer<List<Product>> {
//        Log.v(TAG, "data updated $it")
//        layoutError.visibility = View.GONE
//        layoutNoItem.visibility = View.GONE
//        adapter.update(it)
//    }
//
//    private val isViewLoadingObserver = Observer<Boolean> {
//        Log.v(TAG, "isViewLoading $it")
//        val visibility = if (it) View.VISIBLE else View.GONE
//        progressBar.visibility = visibility
//    }
//
//    private val onMessageErrorObserver = Observer<Any> {
//        Log.v(TAG, "onMessageError $it")
//        layoutError.visibility = View.VISIBLE
//        layoutNoItem.visibility = View.GONE
//        tvError.text = "Error $it"
//    }
//
//    private val emptyListObserver = Observer<Boolean> {
//        Log.v(TAG, "emptyListObserver $it")
//        layoutNoItem.visibility = View.VISIBLE
//        layoutError.visibility = View.GONE
//    }
//
//    override fun onResume() {
//        super.onResume()
//        viewModel.loadProducts()
//    }
}
