package com.example.onlyfriends.model.table

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity
class Subscription (
    @OneToOne
    var author: User,

    @ManyToOne
    var subscriber: User
): AbstractEntity()