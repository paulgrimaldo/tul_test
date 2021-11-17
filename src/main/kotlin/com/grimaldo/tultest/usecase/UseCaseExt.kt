package com.grimaldo.tultest.usecase

import com.grimaldo.tultest.domain.entity.ProductType


fun Double.normalizePrice(productType: ProductType): Double{
    return if (productType == ProductType.SIMPLE) {
        this
    } else {
        this.div(2)
    }
}

data class CartItem(
    val productId: String,
    val quantity: Int
)