package ru.onlyfriends.api.service.userPostService

import ru.onlyfriends.api.model.dto.request.PostRequest
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.service.CrudService

interface PostService : CrudService<PostRequest, Post, Long> {
    fun canUserModeratePost(postId: Long): Boolean
    fun put(postId: Long, postRequest: PostRequest): Post
}