package ru.onlyfriends.api.model.dto.responses

import org.springframework.http.HttpStatus
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.entity.Post
import java.time.LocalDateTime

class PostsResponse(
    posts: List<Post>
) : ApiResponse {
    override val status = HttpStatus.OK
    override val message = "Posts"
    override val timestamp: LocalDateTime = LocalDateTime.now()
    val posts: List<PostResponse>
    init {
        this.posts = List(posts.size) {
            posts[it].run { PostResponse(
                this
            )}
        }
    }

    data class PostResponse(
        val post: Post,
    )
}