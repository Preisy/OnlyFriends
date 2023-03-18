package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.User

data class UserRequest(
    val email: String,
    val password: String,
) : ApiRequest {
    override fun asModel() = User(email, password)
}