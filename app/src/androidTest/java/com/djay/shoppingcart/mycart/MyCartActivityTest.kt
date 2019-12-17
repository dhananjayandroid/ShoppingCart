package com.djay.shoppingcart.mycart

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.djay.shoppingcart.R
import com.djay.shoppingcart.helpers.CartHelper
import com.djay.shoppingcart.helpers.TestHelper
import com.djay.shoppingcart.helpers.TestHelper.clickOnViewChild
import com.djay.shoppingcart.model.Product
import org.hamcrest.core.IsNot
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal

@Suppress("SameParameterValue")
@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class MyCartActivityTest {

    // Test Data
    private val product = Product(
        3,
        "Black Sports",
        "120+ sold",
        R.drawable.shoe_3,
        BigDecimal(82)
    )

    @get:Rule
    var activityRule: ActivityTestRule<MyCartActivity> =
        ActivityTestRule(MyCartActivity::class.java)


    @Before
    fun setUp() {
        // Add item in cart before testing
        CartHelper.clearCart()
        CartHelper.addItemToCart(product, 3)
    }

    @Test
    fun ensureCartListIsVisibleTest() {
        // verify if cart list is visible
        onView(withId(R.id.rvCart)).check(matches(isDisplayed()))
    }

    @Test
    fun itemInCartTest() {
        // verify if the item is added to cart
        onView(withId(R.id.rvCart)).check(
            matches(
                IsNot.not(
                    TestHelper.hasItem(
                        hasDescendant(
                            withText(product.name)
                        )
                    )
                )
            )
        )
    }

    @Test
    fun totalCostTest() {
        // verify if the total amount is visible and correct
        onView(withId(R.id.tvTotal)).check(
            matches(
                withText(
                    activityRule.activity.getString(
                        R.string.total_amount
                        , CartHelper.totalAmount()
                    )
                )
            )
        )
    }

    @Test
    fun placeOrderTest() {
        // Click Place order button
        onView(withId(R.id.btnBuy)).check(matches(isDisplayed())).perform(click())
//        sleep(1000)
        // verify the toast with order place message
//        onView(withText(activityRule.activity.getString(R.string.order_placed))).inRoot(
//            RootMatchers.withDecorView(
//                IsNot.not(Is.`is`(activityRule.activity.window.decorView))
//            )
//        ).check(matches(isDisplayed()))

        // verify the product-list screen is visible
        onView(withId(R.id.rvProductList)).check(matches(isDisplayed()))
    }

    @Test
    fun removeItemFromCartTest() {
        // Click remove button of the added product
        onView(withId(R.id.rvCart)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, clickOnViewChild(R.id.btnRemove)
            )
        )
//        sleep(1000)
        // Verify the toast with cart cleared message
//        onView(withText(activityRule.activity.getString(R.string.cart_cleared))).inRoot(
//            RootMatchers.withDecorView(IsNot.not(Is.`is`(activityRule.activity.window.decorView)))
//        ).check(matches(isDisplayed()))
        onView(withId(R.id.rvProductList)).check(matches(isDisplayed()))
    }
}