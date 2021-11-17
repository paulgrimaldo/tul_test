package com.grimaldo.tultest.usecase.product

import com.grimaldo.tultest.domain.data.ProductRepository
import com.grimaldo.tultest.usecase.BaseUseCase
import org.springframework.stereotype.Component

@Component
class DeleteProduct(
    private val findProductById: FindProductById,
    private val productRepository: ProductRepository
) : BaseUseCase<String, Unit>() {

    override fun invoke(param: String) {
        val product = findProductById(param)

        productRepository.delete(product)
    }
}