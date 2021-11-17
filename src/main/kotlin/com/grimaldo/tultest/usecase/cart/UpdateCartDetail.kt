package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.data.ShoppingCartDetailRepository
import com.grimaldo.tultest.domain.data.ShoppingCartRepository
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.usecase.BaseUseCase
import com.grimaldo.tultest.usecase.CartItem
import com.grimaldo.tultest.usecase.product.FindProductById
import org.springframework.stereotype.Component


@Component
class UpdateCartDetail(
    private val findProductById: FindProductById,
    private val findCartDetail: FindCartDetail,
    private val cartDetailRepository: ShoppingCartDetailRepository,
    private val cartRepository: ShoppingCartRepository
) : BaseUseCase<UpdateCartDetail.Params, ShoppingCart>() {

    override fun invoke(param: Params): ShoppingCart {
        val product = findProductById(param.cartItem.productId)
        val shoppingCart = param.shoppingCart
        val cartDetail = findCartDetail(FindCartDetail.Params(product, shoppingCart))

        shoppingCart.apply {
            total = total!! - cartDetail.subTotal!!
        }
        cartRepository.save(shoppingCart)

        cartDetail.apply {
            quantity = param.cartItem.quantity
            subTotal = param.cartItem.quantity * product.price!!
        }
        cartDetailRepository.save(cartDetail)

        shoppingCart.apply {
            total = total!! + cartDetail.subTotal!!
        }
        cartRepository.save(shoppingCart)

        shoppingCart.apply {
            cartDetails = ArrayList(cartDetails!!.filter {
                it.product!!.id != product.id
            }).also {
                it.add(cartDetail)
            }
        }
        return shoppingCart
    }

    data class Params(
        val shoppingCart: ShoppingCart,
        val cartItem: CartItem
    )
}