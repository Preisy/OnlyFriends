package ru.onlyfriends.api.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class User(
    @Column(length = 255, nullable = false)
    var login: String,
    @Column(length = 255, nullable = false)
    var password: String,
) : AbstractEntity()