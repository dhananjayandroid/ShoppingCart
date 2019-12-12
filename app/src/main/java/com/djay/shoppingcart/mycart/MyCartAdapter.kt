package com.djay.shoppingcart.mycart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.djay.shoppingcart.R
import com.djay.shoppingcart.helpers.CartHelper
import com.djay.shoppingcart.model.Product

class MyCartAdapter :
    RecyclerView.Adapter<MyCartAdapter.MViewHolder>() {

    var onRemoveClick: ((Product) -> Unit)? = null
    var products : ArrayList<Product> = ArrayList(CartHelper.cartItems.keys)

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_cart_item, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        val product = products[position]

        vh.tvTitle.text = product.name
        vh.tvPrice.text = vh.tvPrice.context.getString(R.string._amount, product.price)
        vh.tvQuantity.text = vh.tvQuantity.context.getString(R.string.quantity_, CartHelper.cartItems[product].toString())
        Glide.with(vh.imageView.context).load(product.photo).into(vh.imageView)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    inner class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val imageView: ImageView = view.findViewById(R.id.ivProduct)
        val tvQuantity: TextView = view.findViewById(R.id.tvQuantity)
        private val btnRemove : Button = view.findViewById(R.id.btnRemove)

        init {
            btnRemove.setOnClickListener {
                onRemoveClick?.invoke(products[adapterPosition])
            }
        }
    }

    fun refreshList() {
        products = ArrayList(CartHelper.cartItems.keys)
        notifyDataSetChanged()
    }
}