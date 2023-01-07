package com.example.onlyfriends.model.table

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
class UsersPost(
    @Column(length = 1023)
    var text: String,

    @ManyToOne
    val user: User,

    @Column(nullable = false)
    val dateTime: LocalDateTime = LocalDateTime.now(),
    ) : AbstractEntity()