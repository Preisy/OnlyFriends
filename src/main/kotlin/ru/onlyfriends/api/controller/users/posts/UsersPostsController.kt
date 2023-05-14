package ru.onlyfriends.api.controller.users.posts

import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.service.postService.userPostService.PostService

@RestController
// TODO make different services with
class UsersPostsController (
    val service: PostService
){
    @GetMapping("/users/{id}/posts")
    fun getPosts(
        @PathVariable id: Long,
        @RequestParam("page") page: Int,
        @RequestParam("page_size") pageSize: Int) =
        service.getUsersPosts(id, page, pageSize)

}