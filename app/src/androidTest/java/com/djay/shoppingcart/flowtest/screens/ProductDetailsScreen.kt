package com.djay.shoppingcart.flowtest.screens

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.djay.shoppingcart.R
import com.djay.shoppingcart.model.Product


class ProductDetailsScreen {

    private val plusButton = onView(ViewMatchers.withId(R.id.ibPlus))
    private val addToCartButton = onView(ViewMatchers.withId(R.id.btnAddToCart))
    private val productNameTextView = onView(ViewMatchers.withId(R.id.tvProductName))
    private val productDescriptionTextView = onView(ViewMatchers.withId(R.id.tvDesc))
    private val priceTextView = onView(ViewMatchers.withId(R.id.tvPrice))

    fun addToCart(): MyCartScreen {
        return clickAddToCartButton()
    }

    fun addMultipleQuantityCart(): MyCartScreen {
        increaseQuantity()
        return clickAddToCartButton()
    }

    fun verifyProductDetailsDisplayed(product: Product?): ProductDetailsScreen {
        // Verify the product name is visible
        productNameTextView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            .check(ViewAssertions.matches(ViewMatchers.withText(product?.name)))
        // Verify the product price is visible
        if (product != null) {
            priceTextView.check(
                ViewAssertions.matches(ViewMatchers.withText("$" + product.price))
            )
        }
        // Verify the product description is visible
        productDescriptionTextView.check(
            ViewAssertions.matches(
                ViewMatchers.withText(
                    product?.desc
                )
            )
        )
        return this
    }

    private fun clickAddToCartButton(): MyCartScreen {
        addToCartButton.perform(ViewActions.click())
        return MyCartScreen()
    }

    private fun increaseQuantity(): MyCartScreen {
        plusButton.perform(ViewActions.click())
        return MyCartScreen()
    }
}
