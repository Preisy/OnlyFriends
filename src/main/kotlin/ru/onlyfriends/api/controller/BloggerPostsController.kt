package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.service.postService.ResponseService.PostsResponseService

@RestController
@RequestMapping("/posts/users")
class BloggerPostsController(
    val service: PostsResponseService
) {
//    @GetMapping("/{email}/posts")
    @GetMapping("/{email}")
    fun getPosts(
        @PathVariable email: String,
        @RequestParam("since") since: String,
        @RequestParam("page_size") pageSize: Int) =
        service.getBloggerPosts(email, since, pageSize)

}