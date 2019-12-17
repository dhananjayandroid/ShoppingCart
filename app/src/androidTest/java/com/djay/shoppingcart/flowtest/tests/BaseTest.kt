package com.djay.shoppingcart.flowtest.tests

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.djay.shoppingcart.productlist.ProductListActivity
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.rules.TestName
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@Ignore("Base class")
open class BaseTest {

    /**
     * Provided activity will be launched before each test.
     */
    @get:Rule
    var activityTestRule = ActivityTestRule(ProductListActivity::class.java)

    /**
     * Makes the current test name available inside test methods.
     */
    @get:Rule
    var testName = TestName()

    @Before
    open fun setUp() {
        //launch(ProductListActivity::class.java)
    }
}
