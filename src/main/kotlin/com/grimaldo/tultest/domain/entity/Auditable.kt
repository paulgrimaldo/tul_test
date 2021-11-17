package com.grimaldo.tultest.domain.entity

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Type
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.sql.Timestamp
import java.util.*
import javax.persistence.*

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class Auditable {
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @Column(updatable = false, nullable = false)
    var id: UUID? = null

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    @CreationTimestamp
    var createdAt: Timestamp? = null

    @Column(name = "updated_at")
    @LastModifiedDate
    @UpdateTimestamp
    var updatedAt: Timestamp? = null
}