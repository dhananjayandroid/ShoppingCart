package com.djay.shoppingcart

import com.djay.shoppingcart.mycart.MyCartActivityTest
import com.djay.shoppingcart.productdetails.ProductDetailsActivityTest
import com.djay.shoppingcart.productlist.ProductListActivityTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    ProductListActivityTest::class,
    ProductDetailsActivityTest::class,
    MyCartActivityTest::class
)
class CartTestSuite