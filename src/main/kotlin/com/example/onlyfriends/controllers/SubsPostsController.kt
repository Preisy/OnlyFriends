package com.example.onlyfriends.controllers

import com.example.onlyfriends.services.SubsPostsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class SubsPostsController(
    private val subsPostsService: SubsPostsService
) {
    @GetMapping("/posts/last")
    fun getLast() = subsPostsService.getLast()

    @GetMapping("/posts/next")
    fun getNext(@RequestParam dateTime: String) = subsPostsService.getNext(dateTime)
    @GetMapping("/posts/prev")
    fun getPrev(@RequestParam dateTime: String) = subsPostsService.getPrev(dateTime)

}