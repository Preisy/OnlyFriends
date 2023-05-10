package ru.onlyfriends.api.model.dto.responses

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import org.springframework.http.HttpStatus
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.entity.Post
import java.time.LocalDateTime

class PostsResponse(
    posts: List<Post>,
    likesNumber: List<Long>
) : ApiResponse {
    override val status = HttpStatus.OK
    override val message = "Posts"
    override val timestamp: LocalDateTime = LocalDateTime.now()
    val posts: List<PostResponse>
    init {
        this.posts = List(posts.size) {
            PostResponse(
                posts[it],
                likesNumber[it]
            )
        }
    }

    data class PostResponse(
        @JsonSerialize
        val post: Post,
        @JsonProperty("likes_number")
        val likesNumber: Long
    )
}