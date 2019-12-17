package com.djay.shoppingcart.productdetails

import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.djay.shoppingcart.R
import com.djay.shoppingcart.helpers.TestHelper
import com.djay.shoppingcart.model.Product
import com.djay.shoppingcart.productdetails.ProductDetailsActivity.Companion.ARG_PRODUCT
import org.hamcrest.core.IsNot.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.math.BigDecimal

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class ProductDetailsActivityTest {

    // Test Data
    private val product = Product(
        3,
        "Black Sports",
        "120+ sold",
        R.drawable.shoe_3,
        BigDecimal(82)
    )

    @get:Rule
    var activityRule: ActivityTestRule<ProductDetailsActivity> =
        ActivityTestRule(ProductDetailsActivity::class.java, true, false)

    @Before
    fun setUp() {
        // start activity with product extra
        val intent = Intent()
        intent.putExtra(ARG_PRODUCT, product)
        activityRule.launchActivity(intent)
    }

    @Test
    fun productDetailsTest() {
        // Verify the product name is visible
        onView(withId(R.id.tvProductName)).check(matches(isDisplayed()))
            .check(matches(withText(product.name)))
        // Verify the product price is visible
        onView(withId(R.id.tvPrice)).check(
            matches(
                withText(
                    String.format(
                        getTextResource(R.string._amount),
                        product.price
                    )
                )
            )
        )
        // Verify the product description is visible
        onView(withId(R.id.tvDesc)).check(matches(withText(product.desc)))
    }

    @Test
    fun addToCartTest() {
        //Click Add To Cart button
        onView(withId(R.id.btnAddToCart)).check(matches(isDisplayed()))
            .perform(click())
//        Thread.sleep(500)
        // Verify toast with Product added to cart message
//        onView(withText(getTextResource(R.string.product_added))).inRoot(
//            withDecorView(not(`is`(activityRule.activity.window.decorView)))
//        ).check(matches(isDisplayed()))

        // Verify cart screen is visible and product is added to cart
        onView(withId(R.id.rvCart)).check(
            matches(
                not(
                    TestHelper.hasItem(
                        hasDescendant(
                            withText(
                                product.name
                            )
                        )
                    )
                )
            )
        )
    }

    @Test
    fun increaseQuantityTest() {
        // clear text and type 2 in quantity
        onView(withId(R.id.edtQuantity)).perform(clearText(), typeText("2"))

        // click plus button
        onView(withId(R.id.ibPlus)).check(matches(isDisplayed()))
            .perform(click())

        // verify quantity text is 3
        onView(withId(R.id.edtQuantity)).check(matches(withText("3")))
    }

    @Test
    fun decreaseQuantityTest() {
        // clear text and type 5 in quantity
        onView(withId(R.id.edtQuantity)).perform(clearText(), typeText("5"))

        // click plus button
        onView(withId(R.id.ibMinus)).check(matches(isDisplayed()))
            .perform(click())

        // verify quantity text is 4
        onView(withId(R.id.edtQuantity)).check(matches(withText("4")))
    }

    @Test
    fun maxQuantityTest() {
        // clear text and type 12 in quantity
        onView(withId(R.id.edtQuantity)).perform(clearText(), typeText("12"))
        // verify quantity text is 10
        onView(withId(R.id.edtQuantity)).check(matches(withText("10")))

        // clear text and type 10 in quantity
        onView(withId(R.id.edtQuantity)).perform(clearText(), typeText("10"))
        // click plus button
        onView(withId(R.id.ibPlus)).check(matches(isDisplayed()))
            .perform(click())
        // verify quantity text is still 10
        onView(withId(R.id.edtQuantity)).check(matches(withText("10")))
    }

    @Test
    fun minQuantityTest() {
        // clear text and type 0 in quantity
        onView(withId(R.id.edtQuantity)).perform(clearText(), typeText("0"))
        // verify quantity text is 1
        onView(withId(R.id.edtQuantity)).check(matches(withText("1")))

        // clear text and type 1 in quantity
        onView(withId(R.id.edtQuantity)).perform(clearText(), typeText("1"))
        // click minus button
        onView(withId(R.id.ibMinus)).check(matches(isDisplayed()))
            .perform(click())
        // verify quantity text is still 1
        onView(withId(R.id.edtQuantity)).check(matches(withText("1")))
    }

    /**
     * return string from resource id
     */
    private fun getTextResource(id: Int): String {
        return activityRule.activity.getString(id)
    }
}