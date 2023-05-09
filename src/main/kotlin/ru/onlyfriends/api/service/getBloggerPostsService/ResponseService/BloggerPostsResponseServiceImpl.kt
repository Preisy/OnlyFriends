package ru.onlyfriends.api.service.getBloggerPostsService.ResponseService

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.responses.PostsResponse
import ru.onlyfriends.api.service.getBloggerPostsService.BloggerPostsServiceImpl

@Service
class BloggerPostsResponseServiceImpl(
    val service: BloggerPostsServiceImpl
): BloggerPostsResponseService {
    override fun getBloggerPosts(email: String, since: String, pageSize: Int): ResponseEntity<ApiResponse> {
        val posts = service.getBloggerPosts(email, since, pageSize)
        val resp = PostsResponse(posts).asResponse()
        return resp
    }
}