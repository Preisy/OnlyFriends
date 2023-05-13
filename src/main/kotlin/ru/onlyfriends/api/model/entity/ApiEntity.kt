package ru.onlyfriends.api.model.entity

import java.time.LocalDateTime

interface ApiEntity {
    val id: Long
    val createdAt: LocalDateTime
}