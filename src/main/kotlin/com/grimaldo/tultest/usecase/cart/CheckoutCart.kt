package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.data.ShoppingCartRepository
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.domain.entity.ShoppingCartStatus
import com.grimaldo.tultest.usecase.BaseUseCase
import org.springframework.stereotype.Component

@Component
class CheckoutCart(private val shoppingCartRepository: ShoppingCartRepository) :
    BaseUseCase<ShoppingCart, ShoppingCart>() {

    override fun invoke(param: ShoppingCart): ShoppingCart {
        val recalculatedTotal = param.cartDetails?.stream()?.mapToDouble { it.subTotal!! }?.sum()!!
        param.apply {
            this.total = recalculatedTotal
            this.status = ShoppingCartStatus.COMPLETED
        }

        return shoppingCartRepository.save(param)
    }
}