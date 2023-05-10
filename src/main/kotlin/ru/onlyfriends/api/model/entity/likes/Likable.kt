package ru.onlyfriends.api.model.entity.likes

interface Likable {
    enum class ClassTypes(val value: String) {
        POST("post"),
    }
    val type: ClassTypes
    val id: Long
}