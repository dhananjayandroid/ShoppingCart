package com.djay.shoppingcart.mycart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.djay.shoppingcart.R
import com.djay.shoppingcart.model.Product

class MyCartAdapter(var products: List<Product>) :
    RecyclerView.Adapter<MyCartAdapter.MViewHolder>() {

    var onItemClick: ((Product) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_cart_item, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
//        val product = products[position]

//        vh.tvTitle.text = product.name
//        vh.tvPrice.text = vh.tvPrice.context.getString(R.string._amount, product.price)
//        vh.tvSold.text = product.desc
//        Glide.with(vh.imageView.context).load(product.photo).into(vh.imageView)
    }

    override fun getItemCount(): Int {
//        return products.size
        return 10
    }

    fun update(data: List<Product>) {
        products = data
        notifyDataSetChanged()
    }

    inner class MViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
//        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
//        val imageView: ImageView = view.findViewById(R.id.ivProduct)
//        val tvSold: TextView = view.findViewById(R.id.tvSold)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(products[adapterPosition])
            }
        }
    }
}