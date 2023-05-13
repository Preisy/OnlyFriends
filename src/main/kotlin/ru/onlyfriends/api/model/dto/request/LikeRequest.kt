package ru.onlyfriends.api.model.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.entity.likes.Likable
import ru.onlyfriends.api.model.entity.likes.Like

class LikeRequest(
    @JsonProperty("likable_class")
    val likableClass: Likable.ClassTypes,
    val id: Long
) : ApiRequest {
    lateinit var author: User
    lateinit var likable: Likable

    override fun asModel() = Like(author, likable)


}