package com.grimaldo.tultest.domain.entity

import org.hibernate.annotations.LazyCollection
import org.hibernate.annotations.LazyCollectionOption
import javax.persistence.*


@Entity(name = "shopping_carts")
class ShoppingCart : Auditable() {

    @Column(nullable = false)
    var total: Double? = null

    @Column(nullable = false)
    var user: String? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var status: ShoppingCartStatus? = null

    @OneToMany(mappedBy = "shoppingCart")
    @LazyCollection(LazyCollectionOption.FALSE)
    var cartDetails: Collection<CartDetail>? = null
}