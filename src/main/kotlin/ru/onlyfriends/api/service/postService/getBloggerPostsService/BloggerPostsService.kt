package ru.onlyfriends.api.service.postService.getBloggerPostsService

import ru.onlyfriends.api.model.entity.Post

interface BloggerPostsService {
    fun getBloggerPosts(email: String, since: String, pageSize: Int): List<Post>
}