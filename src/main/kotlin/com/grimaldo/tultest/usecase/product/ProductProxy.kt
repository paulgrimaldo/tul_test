package com.grimaldo.tultest.usecase.product

import org.springframework.stereotype.Component

@Component
data class ProductProxy(
    val findProductById: FindProductById,
    val saveNewProduct: SaveNewProduct,
    val deleteProduct: DeleteProduct,
    val updateProduct: UpdateProduct,
    val listProducts: ListProducts
)