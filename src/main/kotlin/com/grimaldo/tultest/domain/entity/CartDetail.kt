package com.grimaldo.tultest.domain.entity

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.*


@Entity(name = "shopping_cart_details")
class CartDetail {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Column(updatable = false, nullable = false)
    var id: UUID? = null

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    var product: Product? = null

    @Column
    var quantity: Int? = null

    @Column(name = "sub_total")
    var subTotal: Double? = null

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id", referencedColumnName = "id", nullable = false)
    var shoppingCart: ShoppingCart? = null
}