package ru.onlyfriends.api.model.entity

import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne

@Entity
class Subscription (
    @OneToOne
    var blogger: User,

    @ManyToOne
    var subscriber: User
): AbstractEntity()