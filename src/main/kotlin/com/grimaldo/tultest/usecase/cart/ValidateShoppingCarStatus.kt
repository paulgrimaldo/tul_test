package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.domain.entity.ShoppingCartStatus
import com.grimaldo.tultest.domain.error.InvalidShoppingCarStatusException
import com.grimaldo.tultest.usecase.BaseUseCase
import org.springframework.stereotype.Component

@Component
class ValidateShoppingCarStatus : BaseUseCase<ValidateShoppingCarStatus.Params, Unit>() {

    override fun invoke(param: Params) {
        if (param.shoppingCar.status != param.status) {
            InvalidShoppingCarStatusException("Shopping cart status error, expected: ${param.status}, current is ${param.shoppingCar.status}")
        }
    }

    data class Params(
        val shoppingCar: ShoppingCart,
        val status: ShoppingCartStatus
    )
}