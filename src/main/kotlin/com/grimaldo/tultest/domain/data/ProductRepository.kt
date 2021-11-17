package com.grimaldo.tultest.domain.data

import com.grimaldo.tultest.domain.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*


@Repository
interface ProductRepository : JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product>