package com.grimaldo.tultest.domain.data

import com.grimaldo.tultest.domain.entity.CartDetail
import com.grimaldo.tultest.domain.entity.Product
import com.grimaldo.tultest.domain.entity.ShoppingCart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ShoppingCartDetailRepository : JpaRepository<CartDetail, UUID>, JpaSpecificationExecutor<CartDetail> {

    fun findByProductAndShoppingCart(product: Product, shoppingCart: ShoppingCart): Optional<CartDetail>
}