package ru.onlyfriends.api.model.dto.request

import com.fasterxml.jackson.databind.annotation.JsonSerialize

@JsonSerialize
class UpdateUserRequest(
    val email: String? = null,
    val nickname: String? = null,
)