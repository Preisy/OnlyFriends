package ru.onlyfriends.api.model.entity.likes

import jakarta.persistence.*
import ru.onlyfriends.api.model.entity.AbstractEntity
import ru.onlyfriends.api.model.entity.User

@Entity(name = "like_table")
class Like (
    @ManyToOne
    val user: User,
    likable: Likable
) : AbstractEntity() {
    @Column(length = 255, nullable = true)
    val targetType: String?
    @Column
    val targetId: Long

    init {
        targetType = likable.type.value
        targetId = likable.id
    }
}