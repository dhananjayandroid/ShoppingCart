package com.djay.shoppingcart.productlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.djay.shoppingcart.R
import com.djay.shoppingcart.model.Product

class ProductListAdapter(private var products: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.MViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_product, parent, false)
        return MViewHolder(view)
    }

    override fun onBindViewHolder(vh: MViewHolder, position: Int) {
        val museum = products[position]

        //render
        vh.tvPrice.text = museum.name
        vh.tvSold.text = museum.desc
        Glide.with(vh.imageView.context).load(museum.photo).into(vh.imageView)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun update(data: List<Product>) {
        products = data
        notifyDataSetChanged()
    }

    class MViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val imageView: ImageView = view.findViewById(R.id.ivProduct)
        val tvSold: TextView = view.findViewById(R.id.tvSold)
    }
}