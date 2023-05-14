package ru.onlyfriends.api.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import ru.onlyfriends.api.model.entity.files.File
import ru.onlyfriends.api.model.entity.files.WithFiles
import ru.onlyfriends.api.model.entity.like.Likable

@Entity
class Post(
    @ManyToOne(fetch = FetchType.LAZY)
    val author: User,
    @Column(length = 255)
    var text: String,
) : Likable(), WithFiles {
    @OneToMany
    override var files: List<File> = emptyList()
}