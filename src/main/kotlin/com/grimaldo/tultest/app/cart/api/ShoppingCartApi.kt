package com.grimaldo.tultest.app.cart.api

import com.grimaldo.tultest.domain.entity.ShoppingCartStatus
import java.sql.Timestamp

data class ShoppingCartApi(
    val id: String,
    val user: String,
    val status: ShoppingCartStatus,
    val total: Double,
    val createdAt: Timestamp,
    val updatedAt: Timestamp,
    val items: Collection<ShoppingCartDetailApi>?
)

data class ShoppingCartDetailApi(
    val productId: String,
    val productName: String,
    val quantity: Int,
    val subTotal: Double
)