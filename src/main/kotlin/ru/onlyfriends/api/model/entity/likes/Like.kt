package ru.onlyfriends.api.model.entity.likes

import com.fasterxml.jackson.annotation.JsonIdentityInfo
import com.fasterxml.jackson.annotation.JsonIdentityReference
import com.fasterxml.jackson.annotation.ObjectIdGenerators
import jakarta.persistence.*
import ru.onlyfriends.api.model.entity.AbstractEntity
import ru.onlyfriends.api.model.entity.User

@Entity(name = "like_table")
class Like (
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator::class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    val user: User,
    likable: Likable
) : AbstractEntity() {
    @Column(length = 255)
    val targetType: String
    @Column
    val targetId: Long

    init {
        targetType = likable.type.value
        targetId = likable.id
    }
}