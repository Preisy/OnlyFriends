package ru.onlyfriends.api.service.postService.getBloggerPostsService

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.repository.PostRepository
import ru.onlyfriends.api.model.repository.UserRepository

@Service
class UsersPostsServiceImpl(
    val repository: PostRepository,
    val userRepository: UserRepository
) : UsersPostsService {

    override fun getBloggerPosts(id: Long, page: Int, pageSize: Int) =
        repository.findAllByAuthorOrderByCreatedAtDesc(
            userRepository.findById(id).get(),
            PageRequest.of(page, pageSize)
            )
}