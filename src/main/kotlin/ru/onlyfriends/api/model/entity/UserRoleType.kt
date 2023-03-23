package ru.onlyfriends.api.model.entity

enum class UserRoleType {
    Admin,
    Moderator,
    User;

    override fun toString(): String {
        return super.toString().uppercase()
    }
}