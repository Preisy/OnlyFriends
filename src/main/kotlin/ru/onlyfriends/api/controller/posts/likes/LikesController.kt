package ru.onlyfriends.api.controller.posts.likes

import org.springframework.web.bind.annotation.*

//@RestController
//@RequestMapping("/posts/{id}")
//class LikesController(
//    val likeService: LikeService
//) {
//    @PostMapping("/likes")
//    fun setLike(@PathVariable id: Long) =
//        likeService.setLike(LikeRequest(Likable.ClassTypes.POST, id))
//
//    @DeleteMapping("/likes")
//    fun deleteLike(@PathVariable id: Long) =
//        likeService.delete(LikeRequest(Likable.ClassTypes.POST, id))
//
//    @GetMapping("/likes")
//    fun getLikes(
//        @PathVariable id: Long,
//        @RequestParam("page") page: Int,
//        @RequestParam("page_size") pageSize: Int): List<Any> =
//        likeService.getLikes(LikeRequest(Likable.ClassTypes.POST, id), page, pageSize)
//
//    @GetMapping("/likes/is_liked")
//    fun isLiked(@PathVariable id: Long) =
//        likeService.isLiked(LikeRequest(Likable.ClassTypes.POST, id))
//
//}