package ru.onlyfriends.api.service.likeService

import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.exception.NotLikableException
import ru.onlyfriends.api.model.dto.request.LikeRequest
import ru.onlyfriends.api.model.dto.responses.MessageResponse
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.entity.likes.Likable
import ru.onlyfriends.api.model.entity.likes.Like
import ru.onlyfriends.api.model.repository.LikeRepository
import ru.onlyfriends.api.model.repository.PostRepository
import ru.onlyfriends.api.service.CrudServiceImpl
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class LikeServiceImpl(
    override val repository: LikeRepository,
    val postRepository: PostRepository,
) : CrudServiceImpl<LikeRequest, Like, Long, LikeRepository>(), LikeService {
    override fun create(request: LikeRequest): Like {
        request.setData()
        val like = request.asModel()
        return findLikeEntity(request) ?: repository.save(like)
    }

    fun findLikeEntity(request: LikeRequest): Like? =
        request.setData().run { repository.findByUserAndTargetTypeAndTargetId(author, likableClass.value, id) }

    private fun LikeRequest.setData() = apply {
        author = getPrincipal()
        likable = getRepo().findById(id).get()
    }

    private fun LikeRequest.getRepo() =
        try {
            when (likableClass) {
                Likable.ClassTypes.POST -> postRepository
            }
        } catch (e: IllegalArgumentException) {
            throw NotLikableException()
        }

    override fun delete(request: LikeRequest) =
        findLikeEntity(request)?.let {
            repository.delete(it)
            MessageResponse("Deleted")
        } ?: MessageResponse("Not found", HttpStatus.NOT_FOUND)


    override fun getLikes(request: LikeRequest, since: String, pageSize: Int): List<Like> {
        request.setData()
        val pageable = PageRequest.of(0, pageSize)
        return repository.findAllByCreatedAtLessThanAndTargetTypeAndTargetId(
            LocalDateTime.parse(since, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            request.likable.type.value,
            request.id,
            pageable
        )
    }

    override fun setLike(request: LikeRequest) = create(request)


    override fun countLikes(request: LikeRequest): ApiResponse {
        request.setData()
        val number =  repository.countByTargetTypeAndTargetId(
            request.likable.type.value,
            request.id
        )
        return object : MessageResponse("Likes count") {
            val number = number
        }
    }

    override fun isLiked(request: LikeRequest) =
        object : MessageResponse("is liked") {
            val liked = findLikeEntity(request)?.let {true} ?: false
        }

    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}