package com.grimaldo.tultest.app.cart.api

import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

data class SaveShoppingCartApi(
    @NotNull
    val user: String,

    @NotNull.List
    @Min.List(value = [Min(1)])
    @Valid
    val items: Collection<CartItemApi>
)

data class CartItemApi(
    @NotNull
    val productId: String,

    @NotNull
    val quantity: Int
)