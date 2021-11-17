package com.grimaldo.tultest.usecase.product

import com.grimaldo.tultest.domain.data.ProductRepository
import com.grimaldo.tultest.domain.entity.Product
import com.grimaldo.tultest.usecase.BaseUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class ListProducts(private val productRepository: ProductRepository) : BaseUseCase<Pageable, Page<Product>>() {

    override fun invoke(param: Pageable): Page<Product> {
        return productRepository.findAll(param)
    }
}