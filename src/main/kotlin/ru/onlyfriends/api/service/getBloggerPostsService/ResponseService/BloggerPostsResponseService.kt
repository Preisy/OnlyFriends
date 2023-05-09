package ru.onlyfriends.api.service.getBloggerPostsService.ResponseService

import org.springframework.http.ResponseEntity
import ru.onlyfriends.api.model.dto.ApiResponse

interface BloggerPostsResponseService {
    fun getBloggerPosts(email: String, since: String, pageSize: Int): ResponseEntity<ApiResponse>
}