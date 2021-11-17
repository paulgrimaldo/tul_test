package com.grimaldo.tultest.usecase.cart

import org.springframework.stereotype.Component

@Component
data class CartProxy(
    val addProduct: AddProduct,
    val findCartById: FindCartById,
    val removeProduct: RemoveProduct,
    val saveCart: SaveCart,
    val updateCartDetail: UpdateCartDetail,
    val validateShoppingCarStatus: ValidateShoppingCarStatus,
    val checkoutCart: CheckoutCart
)