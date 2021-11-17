package com.grimaldo.tultest.app.product.controller

import com.grimaldo.tultest.app.product.api.ProductApi
import com.grimaldo.tultest.app.product.service.ProductService
import io.github.jhipster.web.util.PaginationUtil
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid


@RestController
@RequestMapping(value = ["api/products"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ProductController(private val productService: ProductService) {

    @GetMapping(value = [""])
    fun listProducts(pageable: Pageable): ResponseEntity<Collection<ProductApi>> {
        val products = productService.listProducts(pageable)

        val headers = PaginationUtil.generatePaginationHttpHeaders(
            ServletUriComponentsBuilder.fromCurrentRequest(),
            products
        )
        return ResponseEntity.ok().headers(headers).body(products.content)
    }

    @GetMapping(value = ["/{productId}"])
    fun findProductById(@PathVariable productId: String): ProductApi {
        return productService.findProduct(productId)
    }

    @PostMapping(value = [""], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun saveNewProduct(@RequestBody @Valid productApi: ProductApi): ProductApi {
        internalValidateProductRequest(productApi)
        return productService.saveNewProduct(productApi)
    }

    //TODO can be replaced with Java Constraint Validation specific class
    private fun internalValidateProductRequest(productApi: ProductApi) {
        if (productApi.id != null) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Body must not have ID"
            )
        }
        if (productApi.createdAt != null || productApi.updatedAt != null) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Invalid request"
            )
        }
    }

    @PutMapping(value = ["/{productId}"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateProduct(@PathVariable productId: String, @RequestBody @Valid productApi: ProductApi): ProductApi {
        internalValidateProductRequest(productApi)
        return productService.updateProduct(productId, productApi)
    }

    @DeleteMapping(value = ["/{productId}"])
    fun deleteProduct(@PathVariable productId: String): ResponseEntity<Any> {
        productService.deleteProduct(productId)

        return ResponseEntity.noContent().build()
    }
}