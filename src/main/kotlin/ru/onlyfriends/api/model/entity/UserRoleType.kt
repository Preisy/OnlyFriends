package ru.onlyfriends.api.model.entity

enum class UserRoleType {
    ADMIN,
    MODERATOR,
    USER;

    override fun toString(): String {
        return super.toString().uppercase()
    }
}