package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.User

data class PostRequest(
    var text: String
) : ApiRequest {
    var author: User? = null

    override fun asModel(): Post = Post(author!!, text)
}