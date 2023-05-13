package ru.onlyfriends.api.service.postService.getBloggerPostsService

import ru.onlyfriends.api.model.entity.Post

interface BloggerPostsService {
    fun getBloggerPosts(id: Long, page: Int, pageSize: Int): List<Post>
}