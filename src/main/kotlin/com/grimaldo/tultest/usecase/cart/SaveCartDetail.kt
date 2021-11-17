package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.data.ShoppingCartDetailRepository
import com.grimaldo.tultest.domain.entity.CartDetail
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.usecase.BaseUseCase
import com.grimaldo.tultest.usecase.CartItem
import com.grimaldo.tultest.usecase.product.FindProductById
import org.springframework.stereotype.Component

@Component
class SaveCartDetail(
    private val findProductById: FindProductById,
    private val cartDetailRepository: ShoppingCartDetailRepository) :
    BaseUseCase<SaveCartDetail.Params, CartDetail>() {

    override fun invoke(param: Params): CartDetail {
        val product = findProductById(param.item.productId)

        val detail = CartDetail().apply {
            this.product = product
            this.quantity    = param.item.quantity
            this.shoppingCart = param.shoppingCart
            this.subTotal = product.price!! * param.item.quantity
        }

        return cartDetailRepository.save(detail)
    }

    data class Params(
        val item: CartItem,
        val shoppingCart: ShoppingCart
    )
}