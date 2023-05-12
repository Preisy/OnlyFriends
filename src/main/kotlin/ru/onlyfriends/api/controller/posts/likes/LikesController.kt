package ru.onlyfriends.api.controller.posts.likes

import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.model.dto.request.LikeRequest
import ru.onlyfriends.api.model.entity.likes.Likable
import ru.onlyfriends.api.service.likeService.LikeService

@RestController
@RequestMapping("/posts")
class LikesController(
    val likeService: LikeService
) {
    @PostMapping("/{id}/likes")
    fun setLike(@PathVariable id: Long) =
        likeService.setLike(LikeRequest(Likable.ClassTypes.POST, id))

    @DeleteMapping("/{id}/likes")
    fun deleteLike(@PathVariable id: Long) =
        likeService.delete(LikeRequest(Likable.ClassTypes.POST, id))

    @GetMapping("/{id}/likes")
    fun getLikes(
        @PathVariable id: Long,
        @RequestParam("since") since: String,
        @RequestParam("page_size") pageSize: Int) =
        likeService.getLikes(LikeRequest(Likable.ClassTypes.POST, id), since, pageSize)

    @GetMapping("/{id}/likes/is_liked")
    fun isLiked(@PathVariable id: Long) =
        likeService.isLiked(LikeRequest(Likable.ClassTypes.POST, id))

    @GetMapping("/{id}/likes/count")
    fun countLiked(@PathVariable id: Long) =
        likeService.countLikes(LikeRequest(Likable.ClassTypes.POST, id))

}