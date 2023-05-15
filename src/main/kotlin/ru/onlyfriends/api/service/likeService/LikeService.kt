package ru.onlyfriends.api.service.likeService

import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.CrudRepository
import org.springframework.security.core.context.SecurityContextHolder
import ru.onlyfriends.api.model.dto.exception.AlreadyLikedException
import ru.onlyfriends.api.model.dto.exception.ResourceNotFoundException
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.entity.like.Likable
import ru.onlyfriends.api.model.entity.like.Like
import ru.onlyfriends.api.model.repository.LikeRepository
import ru.onlyfriends.api.utils.throwIsEmpty
import ru.onlyfriends.api.utils.throwIsNotEmpty


class LikeService<Target : Likable, TargetId : Any>(
    private val likeRepository: LikeRepository,
    private val targetRepository: CrudRepository<Target, TargetId>
) {

    fun getLikes(id: TargetId, page: Int, pageSize: Int): List<Like> {
        val target = findTargetById(id)
        return likeRepository.findByTargetOrderByCreatedAtDesc(target, PageRequest.of(page, pageSize))
    }

    fun like(id: TargetId) {
        val target = findTargetById(id)
        val principal = getPrincipal()
        target.likes.find { it.user.id == principal.id }.throwIsNotEmpty { AlreadyLikedException() }
        val like = Like(principal, target)
        target.likes.add(like)
        targetRepository.save(target)
        likeRepository.save(like)
    }

    fun deleteMyLike(id: TargetId) {
        val target = findTargetById(id)
        val principal = getPrincipal()
        val like = target.likes.find { it.user.id == principal.id }.throwIsEmpty { ResourceNotFoundException("like") }
        target.likes.remove(like)
        likeRepository.delete(like)
        targetRepository.save(target)
    }

    private fun findTargetById(id: TargetId): Target = targetRepository.findById(id).orElseThrow {
        ResourceNotFoundException()
    }

    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}