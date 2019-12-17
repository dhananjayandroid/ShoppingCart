package com.djay.shoppingcart.helpers

import com.djay.shoppingcart.R
import com.djay.shoppingcart.model.Product
import java.math.BigDecimal

class DataHelper {

    companion object {
        fun createDummyProductList(): List<Product> {

            val list = mutableListOf<Product>()

            var item = Product(1, "White Sneaker", "200+ sold", R.drawable.shoe_1, BigDecimal(85))
            list.add(item)

            item = Product(2, "Blue Lace-up", "New arrival", R.drawable.shoe_2, BigDecimal(120))
            list.add(item)

            item = Product(3, "Black Sports", "120+ sold", R.drawable.shoe_3, BigDecimal(82))
            list.add(item)

            item = Product(4, "Black Formal", "800+ sold", R.drawable.shoe_4, BigDecimal(110))
            list.add(item)

            item = Product(5, "Grey Sports", "80+ sold", R.drawable.shoe_5, BigDecimal(105))
            list.add(item)

            item = Product(6, "White Sneaker1", "500+ sold", R.drawable.shoe_1, BigDecimal(78))
            list.add(item)

            item = Product(7, "Blue Lace-up1", "50+ sold", R.drawable.shoe_2, BigDecimal(190))
            list.add(item)

            item = Product(8, "Black Sports1", "50+ sold", R.drawable.shoe_3, BigDecimal(145))
            list.add(item)

            item = Product(9, "Black Formal1", "50+ sold", R.drawable.shoe_4, BigDecimal(130))
            list.add(item)

            item = Product(10, "Grey Sports1", "50+ sold", R.drawable.shoe_5, BigDecimal(110))
            list.add(item)

            item = Product(11, "White Sneaker2", "50+ sold", R.drawable.shoe_1, BigDecimal(100))
            list.add(item)

            item = Product(12, "Blue Lace-up2", "50+ sold", R.drawable.shoe_2, BigDecimal(105))
            list.add(item)

            item = Product(13, "Black Sports2", "50+ sold", R.drawable.shoe_3, BigDecimal(90))
            list.add(item)

            return list
        }
    }
}