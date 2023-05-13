package ru.onlyfriends.api.service.likeService

import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.request.LikeRequest
import ru.onlyfriends.api.model.entity.likes.Like
import ru.onlyfriends.api.service.CrudService

interface LikeService : CrudService<LikeRequest, Like, Long> {
    fun isLiked(request: LikeRequest): ApiResponse
    fun delete(request: LikeRequest): ApiResponse
    fun getLikes(request: LikeRequest, page: Int, pageSize: Int): List<Like>
    fun setLike(request: LikeRequest): Like
    fun countLikes(request: LikeRequest): ApiResponse

}