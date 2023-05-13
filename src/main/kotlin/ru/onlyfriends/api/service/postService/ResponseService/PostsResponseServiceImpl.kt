package ru.onlyfriends.api.service.postService.ResponseService

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.responses.PostsResponse
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.likes.Likable
import ru.onlyfriends.api.model.repository.LikeRepository
import ru.onlyfriends.api.service.likeService.LikeService
import ru.onlyfriends.api.service.postService.getBloggerPostsService.UsersPostsServiceImpl
import ru.onlyfriends.api.service.postService.subscriptionsPostsService.SubscriptionsPostsService

@Service
class PostsResponseServiceImpl(
    val service: UsersPostsServiceImpl,
    val subscriptionsPostsService: SubscriptionsPostsService,
    val likeRepository: LikeRepository,
    val likeService: LikeService
): PostsResponseService {
    override fun getBloggerPosts(id: Long, page: Int, pageSize: Int): ResponseEntity<ApiResponse> {
        val posts = service.getBloggerPosts(id, page, pageSize)
        val isLiked = posts.map {  }
        return PostsResponse(posts, getLikesNumber(posts)).asResponse()
    }

    override fun getSubscriptionsPosts(page: Int, pageSize: Int): ResponseEntity<ApiResponse> {
        val posts = subscriptionsPostsService.getPosts(page, pageSize)
        return PostsResponse(posts, getLikesNumber(posts)).asResponse()
    }

    fun getLikesNumber(posts: List<Post>) = posts.map { post ->
        likeRepository
            .countByTargetTypeAndTargetId(Likable.ClassTypes.POST.value, post.id)
    }
}