package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.service.postService.ResponseService.PostsResponseService

@RestController
// TODO /users/{id}/posts
// TODO make different services with
@RequestMapping("/posts")
class BloggerPostsController(
    val service: PostsResponseService
) {
    @GetMapping("/users/{email}")
    fun getPosts(
        @PathVariable email: String,
        @RequestParam("since") since: String,
        @RequestParam("page_size") pageSize: Int) =
        service.getBloggerPosts(email, since, pageSize)

}