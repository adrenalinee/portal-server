package malibu.portal.server.entity

import jakarta.persistence.MappedSuperclass
import jakarta.persistence.Version
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity: PrimaryKeyEntity() {

    @Version
    val version: Long = 0

//    @DateCreated
    @CreationTimestamp
    var createdAt: LocalDateTime = LocalDateTime.MIN
        protected set

//    @DateUpdated
    @UpdateTimestamp
    var updatedAt: LocalDateTime = LocalDateTime.MIN
        protected set
}