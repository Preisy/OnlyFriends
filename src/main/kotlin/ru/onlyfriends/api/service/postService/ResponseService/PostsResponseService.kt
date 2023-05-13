package ru.onlyfriends.api.service.postService.ResponseService

import org.springframework.http.ResponseEntity
import ru.onlyfriends.api.model.dto.ApiResponse

interface PostsResponseService {
    fun getBloggerPosts(id: Long, page: Int, pageSize: Int): ResponseEntity<ApiResponse>
    fun getSubscriptionsPosts(page: Int, pageSize: Int): ResponseEntity<ApiResponse>
}