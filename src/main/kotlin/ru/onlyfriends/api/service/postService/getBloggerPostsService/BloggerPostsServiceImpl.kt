package ru.onlyfriends.api.service.postService.getBloggerPostsService

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.repository.PostRepository
import ru.onlyfriends.api.model.repository.UserRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Service
class BloggerPostsServiceImpl(
    val repository: PostRepository,
    val userRepository: UserRepository
) : BloggerPostsService {

    override fun getBloggerPosts(id: Long, since: String, pageSize: Int) =
        repository.findAllByAuthorAndCreatedAtLessThanOrderByCreatedAt(
            userRepository.findById(id).get(),
            LocalDateTime.parse(since, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            PageRequest.of(0, pageSize)
            )
}