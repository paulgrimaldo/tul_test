package com.grimaldo.tultest.domain.data

import com.grimaldo.tultest.domain.entity.ShoppingCart
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ShoppingCartRepository : JpaRepository<ShoppingCart, UUID>, JpaSpecificationExecutor<ShoppingCart>