package com.grimaldo.tultest.app.product.service.impl

import com.grimaldo.tultest.app.product.api.ProductApi
import com.grimaldo.tultest.app.product.service.ProductService
import com.grimaldo.tultest.app.product.service.mapper.ProductMapper
import com.grimaldo.tultest.usecase.product.ProductProxy
import com.grimaldo.tultest.usecase.product.SaveNewProduct
import com.grimaldo.tultest.usecase.product.UpdateProduct
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ProductServiceImpl(
    private val productProxy: ProductProxy,
    private val mapper: ProductMapper
) : ProductService {
    override fun listProducts(pageable: Pageable): Page<ProductApi> {
        return productProxy.listProducts(pageable)
            .map { mapper.map(it) }
    }

    override fun saveNewProduct(productData: ProductApi): ProductApi {
        return mapper.map(
            productProxy.saveNewProduct(
                SaveNewProduct.Params(
                    productData.name,
                    productData.sku,
                    productData.description,
                    productData.price,
                    productData.type
                )
            )
        )
    }

    override fun findProduct(productId: String): ProductApi {
        return mapper.map(
            productProxy.findProductById(productId)
        )
    }

    override fun updateProduct(productId: String, productData: ProductApi): ProductApi {
        return mapper.map(
            productProxy.updateProduct(
                UpdateProduct.Params(
                    productId,
                    productData.name,
                    productData.sku,
                    productData.description,
                    productData.price,
                    productData.type
                )
            )
        )
    }

    override fun deleteProduct(productId: String) {
        productProxy.deleteProduct(productId)
    }
}