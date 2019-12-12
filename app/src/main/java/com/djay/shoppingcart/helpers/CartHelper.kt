package com.djay.shoppingcart.helpers

import com.djay.shoppingcart.model.Product
import java.math.BigDecimal

object CartHelper {
    val cartItems: LinkedHashMap<Product, Int> = LinkedHashMap()

    fun addItemToCart(product: Product, quantity: Int) {
        if (!cartItems.containsKey(product))
            cartItems[product] = quantity
        else
            cartItems[product] = cartItems[product]!! + quantity
    }

    fun removeItemFromCart(product: Product) {
        cartItems.remove(product)
    }

    fun clearCart() {
        cartItems.clear()
    }

    fun totalAmount(): String {
        var total: BigDecimal = BigDecimal.ZERO
        cartItems.forEach { (key, value) ->
            run {
                total = total.add(key.price.multiply(BigDecimal(value)))
            }
        }
        return total.setScale(2, BigDecimal.ROUND_HALF_UP).toString()
    }
}