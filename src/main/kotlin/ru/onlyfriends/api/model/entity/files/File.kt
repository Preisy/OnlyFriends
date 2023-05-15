package ru.onlyfriends.api.model.entity.files

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import ru.onlyfriends.api.model.entity.AbstractEntity
import ru.onlyfriends.api.model.entity.User
import java.nio.file.Path
import java.nio.file.Paths

private val uploadsFolderPath: Path = Paths.get("/home/alex/Downloads/OnlyFriends")

@Entity
class File (
    @Column(length = 10)
//    @JsonIgnore
    var type: String,

    @ManyToOne
    @JsonIgnore
    val author: User,
) : AbstractEntity() {

    @get:JsonIgnore
    val name
        get() = "$id.$type"
    @JsonIgnore
    fun getPath(): Path = uploadsFolderPath.resolve("$id.$type")

}