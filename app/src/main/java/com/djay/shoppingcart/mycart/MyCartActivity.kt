package com.djay.shoppingcart.mycart

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.djay.shoppingcart.R
import com.djay.shoppingcart.helpers.CartHelper
import kotlinx.android.synthetic.main.activity_my_cart.*

@Suppress("UNUSED_PARAMETER")
class MyCartActivity : AppCompatActivity() {

    private lateinit var adapter: MyCartAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.activity_my_cart)
        setupUI()
    }

    private fun setupUI() {
        adapter = MyCartAdapter()
        rvCart.layoutManager = LinearLayoutManager(this)
        rvCart.adapter = adapter
        adapter.onRemoveClick = { product ->
            CartHelper.removeItemFromCart(product)
            if (CartHelper.cartItems.isNotEmpty()) {
                adapter.refreshList()
                updateTotal()
            } else {
                Toast.makeText(this, getString(R.string.cart_cleared), Toast.LENGTH_SHORT).show()
                finish()
            }
        }

        updateTotal()
    }

    private fun updateTotal() {
        tvTotal.text = getString(R.string.total_amount, CartHelper.totalAmount())
    }

    fun placeOrder(view: View) {
        CartHelper.clearCart()
        Toast.makeText(this, getString(R.string.order_placed), Toast.LENGTH_SHORT).show()
        finish()
    }
}
