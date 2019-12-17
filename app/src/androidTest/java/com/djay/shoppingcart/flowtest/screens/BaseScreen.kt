package com.djay.shoppingcart.flowtest.screens

import androidx.test.espresso.Espresso

open class BaseScreen {

    fun goToProductList(): ProductListScreen {
        Espresso.pressBack()
        return ProductListScreen()
    }
}