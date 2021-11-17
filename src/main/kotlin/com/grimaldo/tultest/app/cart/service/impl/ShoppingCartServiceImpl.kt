package com.grimaldo.tultest.app.cart.service.impl

import com.grimaldo.tultest.app.cart.api.CartItemApi
import com.grimaldo.tultest.app.cart.api.SaveShoppingCartApi
import com.grimaldo.tultest.app.cart.api.ShoppingCartApi
import com.grimaldo.tultest.app.cart.service.ShoppingCartService
import com.grimaldo.tultest.app.cart.service.mapper.CartMapper
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.domain.entity.ShoppingCartStatus
import com.grimaldo.tultest.usecase.CartItem
import com.grimaldo.tultest.usecase.cart.*
import org.springframework.stereotype.Service

@Service
class ShoppingCartServiceImpl(
    private val mapper: CartMapper,
    private val cartProxy: CartProxy
) : ShoppingCartService {

    override fun findCart(cartId: String): ShoppingCartApi {
        return mapper.map(
            internalFindCart(cartId)
        )
    }

    private fun internalFindCart(cartId: String): ShoppingCart {
        return cartProxy.findCartById(cartId)
    }

    override fun saveCart(cartData: SaveShoppingCartApi): ShoppingCartApi {
        return mapper.map(
            cartProxy.saveCart(
                SaveCart.Params(
                    cartData.user,
                    cartData.items.map { CartItem(it.productId, it.quantity) }
                )
            )
        )
    }

    override fun addProduct(cartId: String, cartItem: CartItemApi): ShoppingCartApi {
        val shoppingCart = internalFindCart(cartId)
        cartProxy.validateShoppingCarStatus(ValidateShoppingCarStatus.Params(shoppingCart, ShoppingCartStatus.OPEN))
        return mapper.map(
            cartProxy.addProduct(AddProduct.Params(shoppingCart, CartItem(cartItem.productId, cartItem.quantity)))
        )
    }

    override fun updateProduct(cartId: String, cartItem: CartItemApi): ShoppingCartApi {
        val shoppingCart = internalFindCart(cartId)
        cartProxy.validateShoppingCarStatus(ValidateShoppingCarStatus.Params(shoppingCart, ShoppingCartStatus.OPEN))
        return mapper.map(
            cartProxy.updateCartDetail(
                UpdateCartDetail.Params(
                    shoppingCart,
                    CartItem(cartItem.productId, cartItem.quantity)
                )
            )
        )
    }

    override fun deleteProduct(cartId: String, productId: String): ShoppingCartApi {
        val shoppingCart = internalFindCart(cartId)
        cartProxy.validateShoppingCarStatus(ValidateShoppingCarStatus.Params(shoppingCart, ShoppingCartStatus.OPEN))
        return mapper.map(
            cartProxy.removeProduct(RemoveProduct.Params(shoppingCart, productId))
        )
    }

    override fun checkoutCart(cartId: String): ShoppingCartApi {
        val shoppingCart = internalFindCart(cartId)
        cartProxy.validateShoppingCarStatus(ValidateShoppingCarStatus.Params(shoppingCart, ShoppingCartStatus.OPEN))
        return mapper.map(
            cartProxy.checkoutCart(shoppingCart)
        )
    }
}