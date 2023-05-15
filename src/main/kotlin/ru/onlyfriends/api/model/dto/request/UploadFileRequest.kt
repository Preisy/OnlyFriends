package ru.onlyfriends.api.model.dto.request

import org.apache.commons.io.FilenameUtils
import org.springframework.web.multipart.MultipartFile
import ru.onlyfriends.api.model.dto.exception.FileHasNoExtensionException
import ru.onlyfriends.api.model.entity.files.File
import ru.onlyfriends.api.model.entity.User

class UploadFileRequest(
    val file: MultipartFile

)  : ApiRequest {
    lateinit var author: User


    fun getExtension(): String = FilenameUtils.getExtension(file.originalFilename)
        ?: throw FileHasNoExtensionException()

    override fun asModel() = File(
        getExtension(),
        author
    )
}