package ru.onlyfriends.api.model.entity

import jakarta.persistence.*
import java.io.Serializable
import java.time.LocalDateTime

@MappedSuperclass
abstract class AbstractEntity : Serializable, ApiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Long = 0

    @Column(nullable = false, updatable = false)
    override val createdAt: LocalDateTime = LocalDateTime.now()
}
