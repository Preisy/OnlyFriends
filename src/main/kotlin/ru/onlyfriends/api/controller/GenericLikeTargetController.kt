package ru.onlyfriends.api.controller

import org.springframework.data.repository.CrudRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.message.DeletedMessage
import ru.onlyfriends.api.model.dto.message.SuccessfullyLikedMessage
import ru.onlyfriends.api.model.entity.like.Likable
import ru.onlyfriends.api.model.repository.LikeRepository
import ru.onlyfriends.api.service.likeService.LikeService

abstract class GenericLikeTargetController<Target : Likable, TargetId : Any>(
    likeRepository: LikeRepository,
    targetRepository: CrudRepository<Target, TargetId>
) {
    private val likeService = LikeService(
        likeRepository,
        targetRepository,
    )
    @PostMapping("/{id}/likes")
    fun like(
        @PathVariable id: TargetId,
    ): ResponseEntity<ApiResponse> {
        likeService.like(id)
        return SuccessfullyLikedMessage().asResponse()
    }
    @GetMapping("/{id}/likes")
    fun getLikes(
        @PathVariable id: TargetId,
        @RequestParam("page") page: Int,
        @RequestParam("page_size") pageSize: Int
    ) = likeService.getLikes(id, page, pageSize)

    @DeleteMapping("/{id}/likes")
    fun deleteMyLike(
        @PathVariable id: TargetId,
    ) : ResponseEntity<ApiResponse> {
        likeService.deleteMyLike(id)
        return DeletedMessage().asResponse()
    }
}