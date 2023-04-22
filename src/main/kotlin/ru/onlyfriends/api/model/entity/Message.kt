package ru.onlyfriends.api.model.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne

@Entity
class Message(
    val text: String,
    @OneToOne
    val author: User,
    @OneToOne
    val recipient: User
) : AbstractEntity()