package com.grimaldo.tultest.usecase.product

import com.grimaldo.tultest.domain.data.ProductRepository
import com.grimaldo.tultest.domain.entity.Product
import com.grimaldo.tultest.domain.entity.ProductType
import com.grimaldo.tultest.usecase.BaseUseCase
import com.grimaldo.tultest.usecase.normalizePrice
import org.springframework.stereotype.Component


@Component
class SaveNewProduct(private val productRepository: ProductRepository) : BaseUseCase<SaveNewProduct.Params, Product>() {

    override fun invoke(param: Params): Product {
        val product = Product().apply {
            name = param.name
            sku = param.sku
            description = param.description
            type = param.type
            price = param.price.normalizePrice(param.type)
        }

        return productRepository.save(product)
    }

    data class Params(
        val name: String,
        val sku: String,
        val description: String?,
        val price: Double,
        val type: ProductType
    )
}