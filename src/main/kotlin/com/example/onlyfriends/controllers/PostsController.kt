package com.example.onlyfriends.controllers

import com.example.onlyfriends.model.dtos.requests.DateDto
import com.example.onlyfriends.model.dtos.requests.PostDto
import com.example.onlyfriends.services.UsersPostsService
import org.springframework.data.jpa.repository.Query
import org.springframework.web.bind.annotation.*

@RestController
class PostsController (
    private val usersPosts: UsersPostsService
) {
    @PostMapping("/me/posts")
    fun createPost(@RequestBody post: PostDto) = usersPosts.create(post)

    @DeleteMapping("/me/posts/{id}")
    fun deletePost(@PathVariable id: Long) = usersPosts.delete(id)

    @GetMapping("/me/posts/last")
    fun getLast() = usersPosts.getLast()

    @GetMapping("/me/posts/next")
    fun getNext(@RequestParam dateTime: String) = usersPosts.getNext(dateTime)
    @GetMapping("/me/posts/prev")
    fun getPrev(@RequestParam dateTime: String) = usersPosts.getPrev(dateTime)

}