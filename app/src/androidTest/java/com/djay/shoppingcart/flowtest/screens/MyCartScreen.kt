package com.djay.shoppingcart.flowtest.screens

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.djay.shoppingcart.R
import com.djay.shoppingcart.helpers.TestHelper
import com.djay.shoppingcart.helpers.TestHelper.clickOnViewChild
import com.djay.shoppingcart.model.Product
import org.hamcrest.core.IsNot

class MyCartScreen : BaseScreen() {

    private val cartItemListRecyclerView = onView(ViewMatchers.withId(R.id.rvCart))
    private val placeOrderButton = onView(ViewMatchers.withId(R.id.btnBuy))
    private val removeItemButtonId = R.id.btnRemove

    fun verifyProductIsInCart(product: Product?): MyCartScreen {
        cartItemListRecyclerView.check(
            ViewAssertions.matches(
                IsNot.not(
                    TestHelper.hasItem(
                        hasDescendant(
                            withText(product?.name)
                        )
                    )
                )
            )
        )
        return this
    }

    fun buyNow(): ProductListScreen {
        // Click Place order button
        placeOrderButton.check(ViewAssertions.matches(ViewMatchers.isDisplayed())).perform(click())
        return ProductListScreen()
    }

    fun removeFirstProductFromCart(): ProductListScreen {
        // Click remove button of the added product
        cartItemListRecyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, clickOnViewChild(removeItemButtonId)
            )
        )
        return ProductListScreen()
    }


    fun verifyCartScreenIsVisible() {
        // Verify product list is Visible
        cartItemListRecyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}