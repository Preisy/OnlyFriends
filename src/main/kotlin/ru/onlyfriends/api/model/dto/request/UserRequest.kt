package ru.onlyfriends.api.model.dto.request

import jakarta.persistence.Column
import ru.onlyfriends.api.model.entity.User

class UserRequest (
    val login: String,
    val password: String,
) : ApiRequest {
    override fun asModel() = User(login, password)
}