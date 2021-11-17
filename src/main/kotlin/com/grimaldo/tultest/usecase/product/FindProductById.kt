package com.grimaldo.tultest.usecase.product

import com.grimaldo.tultest.domain.data.ProductRepository
import com.grimaldo.tultest.domain.entity.Product
import com.grimaldo.tultest.domain.error.ProductNotFoundException
import com.grimaldo.tultest.usecase.BaseUseCase
import org.springframework.stereotype.Component
import java.util.*

@Component
class FindProductById(private val productRepository: ProductRepository) : BaseUseCase<String, Product>() {

    override fun invoke(param: String): Product {
        val uuid = UUID.fromString(param)

        return productRepository.findById(uuid)
            .orElseThrow {
                ProductNotFoundException("No product found with id: $param")
            }
    }
}