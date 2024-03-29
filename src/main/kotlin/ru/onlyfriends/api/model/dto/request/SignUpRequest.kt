package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.User

data class SignUpRequest(
    var email: String,
    var password: String,
    var nickname: String
) : ApiRequest {
    override fun asModel() = User(email, nickname, password)
}