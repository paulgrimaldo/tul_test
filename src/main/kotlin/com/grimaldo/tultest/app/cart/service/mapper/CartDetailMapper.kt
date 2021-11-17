package com.grimaldo.tultest.app.cart.service.mapper

import com.grimaldo.tultest.app.cart.api.ShoppingCartDetailApi
import com.grimaldo.tultest.app.shared.AbstractMapper
import com.grimaldo.tultest.domain.entity.CartDetail
import org.springframework.stereotype.Component

@Component
class CartDetailMapper : AbstractMapper<CartDetail, ShoppingCartDetailApi> {

    override fun map(source: CartDetail): ShoppingCartDetailApi {
        return ShoppingCartDetailApi(
            source.product!!.id.toString(),
            source.product!!.name!!,
            source.quantity!!,
            source.subTotal!!
        )
    }
}