package ru.onlyfriends.api.controller

import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.model.dto.request.LikeRequest
import ru.onlyfriends.api.service.likeService.LikeService

@RestController
// TODO mapping /posts/{id}/likes
@RequestMapping("/likes")
class LikesController(
    val likeService: LikeService
) {
    @PostMapping("/{likedType}/{id}")
    fun setLike(@PathVariable likedType: String,
                @PathVariable id: Long) =
        likeService.setLike(LikeRequest(likedType, id))

    @DeleteMapping("/{likedType}/{id}")
    fun deleteLike(
        @PathVariable likedType: String,
        @PathVariable id: Long) =
        likeService.delete(LikeRequest(likedType, id))

    @GetMapping("/{likedType}/{id}")
    fun getLikes(
        @PathVariable likedType: String,
        @PathVariable id: Long,
        @RequestParam("since") since: String,
        @RequestParam("page_size") pageSize: Int)
    = likeService.getLikes(LikeRequest(likedType, id), since, pageSize)

    @GetMapping("/{likedType}/{id}/is_liked")
    fun isLiked(@PathVariable likedType: String,
                @PathVariable id: Long) =
        likeService.isLiked(LikeRequest(likedType, id))

    @GetMapping("/count/{likedType}/{id}")
    fun countLiked(@PathVariable likedType: String,
                @PathVariable id: Long
    ) = likeService.countLikes(LikeRequest(likedType, id))

}