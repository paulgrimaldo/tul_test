package com.grimaldo.tultest.app.cart.controller

import com.grimaldo.tultest.app.cart.api.CartItemApi
import com.grimaldo.tultest.app.cart.api.SaveShoppingCartApi
import com.grimaldo.tultest.app.cart.api.ShoppingCartApi
import com.grimaldo.tultest.app.cart.service.ShoppingCartService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/api/shopping-carts"], produces = [MediaType.APPLICATION_JSON_VALUE])
class ShoppingCartController(private val shoppingCartService: ShoppingCartService) {

    @PostMapping(value = [""], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun createCart(@RequestBody @Valid cartData: SaveShoppingCartApi): ShoppingCartApi {
        return shoppingCartService.saveCart(cartData)
    }

    @GetMapping(value = ["/{cartId}"])
    fun getCart(@PathVariable cartId: String): ShoppingCartApi {
        return shoppingCartService.findCart(cartId)
    }

    @PostMapping(value = ["/{cartId}/add-product"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun addProduct(@PathVariable cartId: String, @RequestBody @Valid cardItemData: CartItemApi): ShoppingCartApi {
        return shoppingCartService.addProduct(cartId, cardItemData)
    }

    @PutMapping(value = ["/{cartId}/update-product"], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun updateProduct(@PathVariable cartId: String, @RequestBody @Valid cardItemData: CartItemApi): ShoppingCartApi {
        return shoppingCartService.updateProduct(cartId, cardItemData)
    }

    @DeleteMapping(value = ["/{cartId}/remove-products/{productId}"])
    fun removeProduct(@PathVariable cartId: String, @PathVariable productId: String): ResponseEntity<Any> {
        shoppingCartService.deleteProduct(cartId, productId)

        return ResponseEntity.noContent().build()
    }

    @PostMapping(value = ["/{cartId}/checkout"])
    fun checkout(@PathVariable cartId: String): ShoppingCartApi {
        return shoppingCartService.checkoutCart(cartId)
    }
}