package com.example.onlyfriends.model.table

import java.util.concurrent.atomic.AtomicLong


object LikeIdGenerator {
    var id: Long = 0
        get() = AtomicLong(field).get().inc()
}

interface Likeable {
    var likeId: Long
}