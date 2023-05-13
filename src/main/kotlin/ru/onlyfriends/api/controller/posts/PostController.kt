package ru.onlyfriends.api.controller.posts

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.message.DeletedMessage
import ru.onlyfriends.api.model.dto.message.SuccessfullyLikedMessage
import ru.onlyfriends.api.model.dto.request.PostRequest
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.service.postService.userPostService.PostService

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService
) {

    @GetMapping
    fun getAll() = postService.findAll()

    @PostMapping
    fun create(
        @RequestBody postRequest: PostRequest
    ) = postService.create(postRequest)

    @DeleteMapping("/{postId}")
    @PreAuthorize("@PostService.canUserModeratePost(#postId) || hasRole('MODERATOR')")
    fun delete(
        @PathVariable postId: Long
    ): ResponseEntity<ApiResponse> {
        postService.delete(postId)
        return DeletedMessage().asResponse()
    }


    @PutMapping("/{postId}")
    @PreAuthorize("@PostService.canUserModeratePost(#postId) || hasRole('MODERATOR')")
    fun updatePost(
        @PathVariable postId: Long,
        @RequestBody postRequest: PostRequest
    ): Post = postService.put(postId, postRequest)

    @PostMapping("{id}/like")
    fun like(
        @PathVariable id: Long,
    ): ResponseEntity<ApiResponse> {
        postService.like(id)
        return SuccessfullyLikedMessage().asResponse()
    }
}