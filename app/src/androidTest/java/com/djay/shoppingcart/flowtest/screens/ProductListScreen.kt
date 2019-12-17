package com.djay.shoppingcart.flowtest.screens


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.djay.shoppingcart.R
import com.djay.shoppingcart.helpers.TestHelper.withProductTitle

class ProductListScreen {

    private val productListRecyclerView = onView(ViewMatchers.withId(R.id.rvProductList))

    fun clickProductItem(productName: String): ProductDetailsScreen {
        productListRecyclerView.perform(
            RecyclerViewActions.actionOnHolderItem<RecyclerView.ViewHolder>(
                withProductTitle(productName), ViewActions.click()
            )
        )
        return ProductDetailsScreen()
    }

    fun verifyProductListIsVisible() {
        // Verify product list is Visible
        productListRecyclerView.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun verifyProductListIsNotVisible() : MyCartScreen{
        // Verify product list is not Visible
        productListRecyclerView.check(doesNotExist())
        return MyCartScreen()
    }
}