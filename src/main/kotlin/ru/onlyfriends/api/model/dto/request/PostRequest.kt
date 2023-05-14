package ru.onlyfriends.api.model.dto.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.entity.files.File

class PostRequest(
    var text: String,
    @JsonProperty("files")
    override val filesIds: List<Long>,
) : ApiRequest, WithFilesRequest {
    var author: User? = null
    @JsonIgnore
    override lateinit var files: List<File>


    override fun asModel()= Post(author!!, text).also {
        it.files = files
    }
}