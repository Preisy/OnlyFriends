package com.example.onlyfriends.model.table

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity
class Subscription (
    @OneToOne
    val author: User,

    @ManyToOne
    val subscriber: User
): AbstractEntity()