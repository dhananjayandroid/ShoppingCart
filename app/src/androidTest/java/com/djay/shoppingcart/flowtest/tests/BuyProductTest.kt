package com.djay.shoppingcart.flowtest.tests

import androidx.test.filters.LargeTest
import com.djay.shoppingcart.R
import com.djay.shoppingcart.flowtest.screens.ProductListScreen
import com.djay.shoppingcart.model.Product
import org.junit.Test
import java.math.BigDecimal

@LargeTest
class BuyProductTest : BaseTest() {

    @Test
    fun buyAProduct() {
        ProductListScreen()
            .clickProductItem(product.name)
            .verifyProductDetailsDisplayed(product)
            .addToCart()
            .verifyProductIsInCart(product)
            .buyNow()
            .verifyProductListIsVisible()
    }

    @Test
    fun buyMultipleProduct() {
        ProductListScreen()
            .clickProductItem(product.name)
            .verifyProductDetailsDisplayed(product)
            .addToCart()
            .verifyProductIsInCart(product)
            .goToProductList()
            .clickProductItem(product1.name)
            .verifyProductDetailsDisplayed(product1)
            .addToCart()
            .verifyProductIsInCart(product1)
            .buyNow()
            .verifyProductListIsVisible()
    }

    @Test
    fun buyMultipleQuantity() {
        ProductListScreen()
            .clickProductItem(product.name)
            .verifyProductDetailsDisplayed(product)
            .addMultipleQuantityCart()
            .verifyProductIsInCart(product)
            .buyNow()
            .verifyProductListIsVisible()
    }

    @Test
    fun addProductAndRemoveFromCart() {
        ProductListScreen()
            .clickProductItem(product.name)
            .verifyProductDetailsDisplayed(product)
            .addToCart()
            .verifyProductIsInCart(product)
            .removeFirstProductFromCart()
            .verifyProductListIsVisible()
    }

    @Test
    fun addMultipleAndRemoveFirstProductFromCart() {
        ProductListScreen()
            .clickProductItem(product.name)
            .verifyProductDetailsDisplayed(product)
            .addToCart()
            .verifyProductIsInCart(product)
            .goToProductList()
            .clickProductItem(product1.name)
            .verifyProductDetailsDisplayed(product1)
            .addToCart()
            .verifyProductIsInCart(product1)
            .removeFirstProductFromCart()
            .verifyProductListIsNotVisible()
            .verifyCartScreenIsVisible()
    }

    companion object {
        private var product = Product(
            3, "Black Sports", "120+ sold", R.drawable.shoe_3, BigDecimal(82)
        )
        private var product1 =
            Product(6, "White Sneaker1", "500+ sold", R.drawable.shoe_1, BigDecimal(78))
    }
}