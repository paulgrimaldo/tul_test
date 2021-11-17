package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.data.ShoppingCartRepository
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.domain.entity.ShoppingCartStatus
import com.grimaldo.tultest.usecase.BaseUseCase
import com.grimaldo.tultest.usecase.CartItem
import org.springframework.stereotype.Component

@Component
class SaveCart(
    private val saveCartDetail: SaveCartDetail,
    private val shoppingCarRepository: ShoppingCartRepository,
) : BaseUseCase<SaveCart.Params, ShoppingCart>() {

    override fun invoke(param: Params): ShoppingCart {
        val cart = ShoppingCart().apply {
            this.status = ShoppingCartStatus.OPEN
            this.user = param.user
            this.total = 0.0
            this.cartDetails = mutableListOf()
        }
        shoppingCarRepository.save(cart)

        var cartTotal = 0.0
        param.items.forEach {
            val cartDetail = saveCartDetail(SaveCartDetail.Params(it, cart))
            cartTotal += cartDetail.subTotal!!
            (cart.cartDetails as MutableList).add(cartDetail)
        }
        cart.apply {
            this.total = cartTotal
        }
        return shoppingCarRepository.save(cart)
    }

    data class Params(
        val user: String,
        val items: Collection<CartItem>
    )
}