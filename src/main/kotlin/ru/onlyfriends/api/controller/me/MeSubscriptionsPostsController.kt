package ru.onlyfriends.api.controller.me

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.service.postService.ResponseService.PostsResponseService

@RestController
@RequestMapping("me/subscriptions/posts")
class MeSubscriptionsPostsController(
    val service: PostsResponseService
) {
    @GetMapping
    fun getPosts(
        @RequestParam("page") page: Int,
        @RequestParam("page_size") pageSize: Int) =
        service.getSubscriptionsPosts(page, pageSize)

}