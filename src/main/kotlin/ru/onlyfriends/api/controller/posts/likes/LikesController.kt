package ru.onlyfriends.api.controller.posts.likes

import org.springframework.web.bind.annotation.*
import ru.onlyfriends.api.controller.GenericLikeTargetController
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.repository.LikeRepository
import ru.onlyfriends.api.model.repository.PostRepository

@RestController
@RequestMapping("/posts")
class LikesController(
    likeRepository: LikeRepository,
    postRepository: PostRepository
) : GenericLikeTargetController<Post, Long>(
    likeRepository,
    postRepository
)