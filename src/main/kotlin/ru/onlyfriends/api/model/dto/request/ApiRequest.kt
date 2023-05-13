package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.ApiEntity

interface ApiRequest {
    fun asModel(): ApiEntity
}