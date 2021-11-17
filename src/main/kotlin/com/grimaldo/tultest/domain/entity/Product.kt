package com.grimaldo.tultest.domain.entity

import javax.persistence.*


@Entity(name = "products")
class Product : Auditable() {
    @Column(nullable = false, length = 255)
    var name: String? = null

    @Column(nullable = false, length = 255)
    var sku: String? = null

    @Lob
    @Column(length = 512)
    var description: String? = null

    @Column(nullable = false)
    var price: Double? = null

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    var type: ProductType? = null
}