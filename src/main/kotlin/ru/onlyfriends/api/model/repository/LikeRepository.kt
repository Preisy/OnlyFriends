package ru.onlyfriends.api.model.repository

import org.springframework.data.repository.CrudRepository
import ru.onlyfriends.api.model.entity.like.Like

interface LikeRepository : CrudRepository<Like, Long>