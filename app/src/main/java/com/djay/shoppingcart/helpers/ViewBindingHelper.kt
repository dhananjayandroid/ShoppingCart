package com.djay.shoppingcart.helpers

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class ViewBindingHelper {

    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun loadImage(view: ImageView, imageUrl: Int?) {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        }
    }
}