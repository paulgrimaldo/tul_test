package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.data.ShoppingCartDetailRepository
import com.grimaldo.tultest.domain.entity.CartDetail
import com.grimaldo.tultest.domain.entity.Product
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.domain.error.CartItemNotFoundException
import com.grimaldo.tultest.usecase.BaseUseCase
import org.springframework.stereotype.Component

@Component
class FindCartDetail(private val cartDetailRepository: ShoppingCartDetailRepository) :
    BaseUseCase<FindCartDetail.Params, CartDetail>() {

    override fun invoke(param: Params): CartDetail {
        return cartDetailRepository.findByProductAndShoppingCart(param.product, param.shoppingCart)
            .orElseThrow {
                CartItemNotFoundException("No item found in cart")
            }
    }

    data class Params(
        val product: Product,
        val shoppingCart: ShoppingCart
    )
}