package com.example.onlyfriends.model.table

import jakarta.persistence.*
import java.io.Serializable

@MappedSuperclass
abstract class AbstractEntity : Serializable {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
        private set
}