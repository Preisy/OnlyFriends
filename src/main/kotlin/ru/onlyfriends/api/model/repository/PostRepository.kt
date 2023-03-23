package ru.onlyfriends.api.model.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.onlyfriends.api.model.entity.Post

@Repository
interface PostRepository : CrudRepository<Post, Long>