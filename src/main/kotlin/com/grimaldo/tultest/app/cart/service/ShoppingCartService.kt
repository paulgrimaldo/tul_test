package com.grimaldo.tultest.app.cart.service

import com.grimaldo.tultest.app.cart.api.CartItemApi
import com.grimaldo.tultest.app.cart.api.SaveShoppingCartApi
import com.grimaldo.tultest.app.cart.api.ShoppingCartApi

interface ShoppingCartService {
    fun findCart(cartId: String): ShoppingCartApi

    fun saveCart(cartData: SaveShoppingCartApi): ShoppingCartApi

    fun addProduct(cartId: String, cartItem: CartItemApi): ShoppingCartApi

    fun updateProduct(cartId: String, cartItem: CartItemApi): ShoppingCartApi

    fun deleteProduct(cartId: String, productId: String): ShoppingCartApi

    fun checkoutCart(cartId: String): ShoppingCartApi
}