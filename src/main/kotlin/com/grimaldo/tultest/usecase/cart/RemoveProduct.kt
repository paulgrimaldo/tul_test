package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.data.ShoppingCartDetailRepository
import com.grimaldo.tultest.domain.data.ShoppingCartRepository
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.usecase.BaseUseCase
import com.grimaldo.tultest.usecase.product.FindProductById
import org.springframework.stereotype.Component
import java.util.*

@Component
class RemoveProduct(
    private val cartDetailRepository: ShoppingCartDetailRepository,
    private val cartRepository: ShoppingCartRepository,
    private val findProductById: FindProductById,
    private val findCartDetail: FindCartDetail
) : BaseUseCase<RemoveProduct.Params, ShoppingCart>() {

    override fun invoke(param: Params): ShoppingCart {
        val shoppingCart = param.shoppingCart
        val product = findProductById(param.productId)

        val cartDetail = findCartDetail(FindCartDetail.Params(product, shoppingCart))

        val amountToRemove = cartDetail.subTotal
        cartDetailRepository.delete(cartDetail)

        shoppingCart.apply {
            total = total!! - amountToRemove!!
            cartDetails = cartDetails!!.filter {
                it.product!!.id!! != UUID.fromString(param.productId)
            }
        }
        return cartRepository.save(shoppingCart)
    }

    data class Params(
        val shoppingCart: ShoppingCart,
        val productId: String
    )
}