package ru.onlyfriends.api.service.postService.userPostService

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.exception.ResourceNotFoundException
import ru.onlyfriends.api.model.dto.request.PostRequest
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.PostRepository
import ru.onlyfriends.api.service.CrudServiceImpl

@Service("PostService")
class PostServiceImpl(
    override val repository: PostRepository
) : PostService, CrudServiceImpl<PostRequest, Post, Long, PostRepository>() {

    override fun create(request: PostRequest): Post {
        val user = getPrincipal()
        request.author = user
        return repository.save(request.asModel())
    }

    override fun put(postId: Long, postRequest: PostRequest): Post {
        return findById(postId).run {
            text = postRequest.text

            repository.save(this)
        }
    }

    override fun canUserModeratePost(postId: Long): Boolean {
        return try {
            val user = getPrincipal()
            val post = findById(postId)
            post.author.id == user.id
        } catch (e: ResourceNotFoundException) {
            false
        }
    }

    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User
}