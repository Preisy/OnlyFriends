package ru.onlyfriends.api.service.getBloggerPostsService

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

    override fun getBloggerPosts(email: String, since: String, pageSize: Int) =
        repository.findAllByAuthorAndCreatedAtLessThanOrderByCreatedAt(
            userRepository.findByEmail(email).get(),
            LocalDateTime.parse(since, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            PageRequest.of(0, pageSize)
            )
}