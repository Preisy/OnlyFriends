package com.example.onlyfriends.model.dtos.requests

import com.example.onlyfriends.model.table.User
import com.example.onlyfriends.model.table.UsersPost

class PostDto (
    val text: String
) {
    fun asEntity(user: User) = UsersPost(
        text = text,
        user = user
    )
}