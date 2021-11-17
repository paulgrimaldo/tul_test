package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.data.ShoppingCartRepository
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.domain.error.CartNotFoundException
import com.grimaldo.tultest.usecase.BaseUseCase
import org.springframework.stereotype.Component
import java.util.*


@Component
class FindCartById(private val shoppingCartRepository: ShoppingCartRepository) : BaseUseCase<String, ShoppingCart>() {

    override fun invoke(param: String): ShoppingCart {
        val uuid = UUID.fromString(param)

        return shoppingCartRepository.findById(uuid)
            .orElseThrow {
                CartNotFoundException("No cart found")
            }
    }
}