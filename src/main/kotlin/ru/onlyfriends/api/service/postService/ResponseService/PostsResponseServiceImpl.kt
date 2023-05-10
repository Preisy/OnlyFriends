package ru.onlyfriends.api.service.postService.ResponseService

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.responses.PostsResponse
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.likes.Likable
import ru.onlyfriends.api.model.repository.LikeRepository
import ru.onlyfriends.api.service.postService.getBloggerPostsService.BloggerPostsServiceImpl
import ru.onlyfriends.api.service.postService.subscriptionsPostsService.SubscriptionsPostsService

@Service
class PostsResponseServiceImpl(
    val service: BloggerPostsServiceImpl,
    val subscriptionsPostsService: SubscriptionsPostsService,
    val likeRepository: LikeRepository
): PostsResponseService {
    override fun getBloggerPosts(email: String, since: String, pageSize: Int): ResponseEntity<ApiResponse> {
        val posts = service.getBloggerPosts(email, since, pageSize)
        return PostsResponse(posts, getLikesNumber(posts)).asResponse()
    }

    override fun getSubscriptionsPosts(since: String, pageSize: Int): ResponseEntity<ApiResponse> {
        val posts = subscriptionsPostsService.getPosts(since, pageSize)
        return PostsResponse(posts, getLikesNumber(posts)).asResponse()
    }

    fun getLikesNumber(posts: List<Post>) = posts.map { post ->
        likeRepository
            .countByTargetTypeAndTargetId(Likable.ClassTypes.POST.value, post.id)
    }
}