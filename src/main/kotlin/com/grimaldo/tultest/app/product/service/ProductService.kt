package com.grimaldo.tultest.app.product.service

import com.grimaldo.tultest.app.product.api.ProductApi
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface ProductService {

    fun listProducts(pageable: Pageable): Page<ProductApi>

    fun saveNewProduct(productData: ProductApi): ProductApi

    fun findProduct(productId: String): ProductApi

    fun updateProduct(productId: String, productData: ProductApi): ProductApi

    fun deleteProduct(productId: String)
}