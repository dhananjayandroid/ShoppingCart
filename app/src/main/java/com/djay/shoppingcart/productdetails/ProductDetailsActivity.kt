package com.djay.shoppingcart.productdetails

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.djay.shoppingcart.BR
import com.djay.shoppingcart.R
import com.djay.shoppingcart.databinding.ActivityProductDetailsBinding
import com.djay.shoppingcart.helpers.CartHelper
import com.djay.shoppingcart.model.Product
import com.djay.shoppingcart.mycart.MyCartActivity
import kotlinx.android.synthetic.main.activity_product_details.*


@Suppress("UNUSED_PARAMETER")
class ProductDetailsActivity : AppCompatActivity() {

    lateinit var product: Product

    companion object {
        const val PRODUCT = "Product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)

        initUI()
        attachQuantityViewer()
    }

    private fun attachQuantityViewer() {
        edtQuantity.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val quantity = Integer.parseInt(s.toString())
                when {
                    quantity > 10 -> edtQuantity.setText(getString(R.string.ten))
                    quantity < 1 -> edtQuantity.setText("1")
                }
            }
        })
    }

    private fun initUI() {
        val binding: ActivityProductDetailsBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_product_details)
        product = intent.extras?.getSerializable(PRODUCT) as Product
        binding.setVariable(BR.product, product)
        binding.executePendingBindings()
    }

    fun addToCart(view: View) {
        CartHelper.addItemToCart(product, Integer.parseInt(edtQuantity.text.toString()))
        val intent = Intent(this, MyCartActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun increaseQuantity(view: View) {
        val quantity = Integer.parseInt(edtQuantity.text.toString())
        edtQuantity.setText((quantity + 1).toString())
    }

    fun decreaseQuantity(view: View) {
        val quantity = Integer.parseInt(edtQuantity.text.toString())
        edtQuantity.setText((quantity - 1).toString())
    }
}
