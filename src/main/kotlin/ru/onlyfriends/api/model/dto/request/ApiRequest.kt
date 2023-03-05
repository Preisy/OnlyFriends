package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.AbstractEntity

interface ApiRequest {
    fun asModel(): AbstractEntity
}