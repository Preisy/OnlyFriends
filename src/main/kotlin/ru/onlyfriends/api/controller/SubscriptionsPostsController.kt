package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.onlyfriends.api.service.postService.ResponseService.PostsResponseService

@RestController
@RequestMapping("/posts/subscriptions")
class SubscriptionsPostsController(
    val service: PostsResponseService
) {
    @GetMapping
    fun getPosts(
        @RequestParam("since") since: String,
        @RequestParam("page_size") pageSize: Int) =
        service.getSubscriptionsPosts(since, pageSize)

}