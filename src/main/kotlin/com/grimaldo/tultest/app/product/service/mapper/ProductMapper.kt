package com.grimaldo.tultest.app.product.service.mapper

import com.grimaldo.tultest.app.product.api.ProductApi
import com.grimaldo.tultest.app.shared.AbstractMapper
import com.grimaldo.tultest.domain.entity.Product
import org.springframework.stereotype.Component

@Component
class ProductMapper : AbstractMapper<Product, ProductApi> {

    override fun map(source: Product): ProductApi {
        return ProductApi(
            source.id!!.toString(),
            source.name!!,
            source.description,
            source.sku!!,
            source.price!!,
            source.type!!,
            source.createdAt,
            source.updatedAt
        )
    }
}