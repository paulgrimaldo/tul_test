package com.grimaldo.tultest.app.cart.service.mapper

import com.grimaldo.tultest.app.cart.api.ShoppingCartApi
import com.grimaldo.tultest.app.shared.AbstractMapper
import com.grimaldo.tultest.domain.entity.ShoppingCart
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class CartMapper(private val cartDetailMapper: CartDetailMapper) : AbstractMapper<ShoppingCart, ShoppingCartApi> {

    override fun map(source: ShoppingCart): ShoppingCartApi {
        return ShoppingCartApi(
            source.id!!.toString(),
            source.user!!,
            source.status!!,
            source.total!!,
            source.createdAt!!,
            source.updatedAt!!,
            source.cartDetails?.stream()?.map {
                cartDetailMapper.map(it)
            }?.collect(Collectors.toList())
        )
    }
}