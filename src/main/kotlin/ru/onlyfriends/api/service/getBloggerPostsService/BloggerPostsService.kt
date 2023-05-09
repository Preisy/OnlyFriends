package ru.onlyfriends.api.service.getBloggerPostsService

import ru.onlyfriends.api.model.entity.Post

interface BloggerPostsService {
    fun getBloggerPosts(email: String, since: String, pageSize: Int): List<Post>
}