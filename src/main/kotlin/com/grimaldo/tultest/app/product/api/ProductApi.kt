package com.grimaldo.tultest.app.product.api

import com.grimaldo.tultest.domain.entity.ProductType
import java.sql.Timestamp
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class ProductApi(
    val id: String?,

    @NotNull(message = "Name is required")
    @NotBlank(message = "Name is required")
    val name: String,
    val description: String?,

    @NotNull(message = "Name is required")
    @NotBlank(message = "SKU is required")
    val sku: String,

    @NotNull(message = "Name is required")
    @NotBlank(message = "Price is required")
    val price: Double,

    @NotNull(message = "Name is required")
    @NotBlank(message = "Product type is required")
    val type: ProductType,
    val createdAt: Timestamp?,
    val updatedAt: Timestamp?
)