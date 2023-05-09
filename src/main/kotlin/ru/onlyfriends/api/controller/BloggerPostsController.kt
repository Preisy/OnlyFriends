package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.service.getBloggerPostsService.ResponseService.BloggerPostsResponseService

@RestController
@RequestMapping("/posts/users")
class BloggerPostsController(
    val service: BloggerPostsResponseService
) {
//    @GetMapping("/{email}/posts")
    @GetMapping("/{email}")
    fun getPosts(
        @PathVariable email: String,
        @RequestParam("since") since: String,
        @RequestParam("page_size") pageSize: Int) =
        service.getBloggerPosts(email, since, pageSize)

}