package com.djay.shoppingcart.model

import java.io.Serializable
import java.math.BigDecimal

data class Product(
    val id: Int,
    val name: String,
    val desc: String,
    val photo: Int,
    val price: BigDecimal
) : Serializable