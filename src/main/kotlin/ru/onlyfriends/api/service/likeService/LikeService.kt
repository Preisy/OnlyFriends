package ru.onlyfriends.api.service.likeService

import ru.onlyfriends.api.model.dto.request.LikeRequest
import ru.onlyfriends.api.model.entity.likes.Like
import ru.onlyfriends.api.service.CrudService

interface LikeService : CrudService<LikeRequest, Like, Long> {
    fun isLiked(request: LikeRequest): Boolean
    fun delete(request: LikeRequest): Boolean
    fun getLikes(request: LikeRequest, since: String, pageSize: Int): List<Like>
    fun setLike(request: LikeRequest): Like
    fun countLikes(request: LikeRequest): Long

}