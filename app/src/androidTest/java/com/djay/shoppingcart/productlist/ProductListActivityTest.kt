package com.djay.shoppingcart.productlist

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.djay.shoppingcart.R
import com.djay.shoppingcart.helpers.DataHelper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class ProductListActivityTest {

    // Test Data
    private val productList = DataHelper.createDummyProductList()

    @get:Rule
    var activityRule: ActivityTestRule<ProductListActivity> =
        ActivityTestRule(ProductListActivity::class.java)

    @Test
    fun ensureProductListIsVisibleTest() {
        // Verify product list is Visible
        onView(withId(R.id.rvProductList)).check(matches(isDisplayed()))
    }

    @Test
    fun productClickTest() {
        // Click on product list item
        onView(withId(R.id.rvProductList))
            .perform(
                actionOnItemAtPosition<ProductListAdapter.MViewHolder>(
                    3,
                    click()
                )
            )

        // Verify product details is screen visible
        onView(withId(R.id.tvProductName)).check(matches(withText(productList[3].name)))
    }
}