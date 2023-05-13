package ru.onlyfriends.api.controller

import org.springframework.data.repository.CrudRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
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
    fun getAllLikes(
        @PathVariable id: TargetId,
    ) = likeService.getAllLikes(id)

    @DeleteMapping("/{id}/likes")
    fun deleteMyLike(
        @PathVariable id: TargetId,
    ) : ResponseEntity<ApiResponse> {
        likeService.deleteMyLike(id)
        return DeletedMessage().asResponse()
    }
}