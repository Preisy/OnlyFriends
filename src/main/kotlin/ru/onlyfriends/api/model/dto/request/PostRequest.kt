package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.Post
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.entity.files.File

class PostRequest(
    var text: String,
    override val filesIds: List<Long>,
) : ApiRequest, WithFilesRequest {
    var author: User? = null
    override lateinit var files: List<File>


    override fun asModel(): Post = Post(author!!, text, files)
}