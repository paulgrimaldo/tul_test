package com.grimaldo.tultest.usecase.cart

import com.grimaldo.tultest.domain.data.ShoppingCartDetailRepository
import com.grimaldo.tultest.domain.data.ShoppingCartRepository
import com.grimaldo.tultest.domain.entity.ShoppingCart
import com.grimaldo.tultest.usecase.BaseUseCase
import com.grimaldo.tultest.usecase.CartItem
import com.grimaldo.tultest.usecase.product.FindProductById
import org.springframework.stereotype.Component

@Component
class AddProduct(
    private val saveCartDetail: SaveCartDetail,
    private val cartDetailRepository: ShoppingCartDetailRepository,
    private val findProductById: FindProductById,
    private val updateCartDetail: UpdateCartDetail,
    private val cartRepository: ShoppingCartRepository,
) : BaseUseCase<AddProduct.Params, ShoppingCart>() {

    override fun invoke(param: Params): ShoppingCart {
        val shoppingCart = param.shoppingCart
        val product = findProductById(param.item.productId)
        val cartDetailOpt = cartDetailRepository.findByProductAndShoppingCart(product, shoppingCart)

        if (cartDetailOpt.isPresent) {
            return updateCartDetail(UpdateCartDetail.Params(shoppingCart, param.item))
        }

        val cartDetail = saveCartDetail(SaveCartDetail.Params(param.item, shoppingCart))

        shoppingCart.apply {
            total = total!! + cartDetail.subTotal!!
            cartDetails = ArrayList(cartDetails!!).also {
                it.add(cartDetail)
            }
        }
        return cartRepository.save(shoppingCart)
    }

    data class Params(
        val shoppingCart: ShoppingCart,
        val item: CartItem
    )
}