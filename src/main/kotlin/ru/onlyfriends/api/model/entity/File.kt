package ru.onlyfriends.api.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.OneToOne
import java.nio.file.Path
import java.nio.file.Paths

private val uploadsFolderPath: Path = Paths.get("/home/alex/Downloads/OnlyFriends")

@Entity
class File (
    @Column(length = 10)
    var type: String,

    @OneToOne
    val author: User
) : AbstractEntity() {
    @JsonIgnore
    fun getName() = "$id.$type"
    @JsonIgnore
    fun getPath() = uploadsFolderPath.resolve("$id.$type")

}