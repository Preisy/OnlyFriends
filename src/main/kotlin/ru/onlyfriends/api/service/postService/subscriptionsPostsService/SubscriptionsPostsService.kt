package ru.onlyfriends.api.service.postService.subscriptionsPostsService

import ru.onlyfriends.api.model.entity.Post

interface SubscriptionsPostsService {
    fun getPosts(since: String, pageSize: Int): List<Post>
}