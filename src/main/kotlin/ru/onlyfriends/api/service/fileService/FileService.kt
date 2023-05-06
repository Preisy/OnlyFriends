package ru.onlyfriends.api.service.fileService

import jakarta.servlet.http.HttpServletResponse
import ru.onlyfriends.api.model.dto.request.FileRequest
import ru.onlyfriends.api.model.entity.File
import ru.onlyfriends.api.service.CrudService

interface FileService : CrudService<FileRequest, File, Long> {
    fun canUserModeratePost(fileId: Long): Boolean
    fun getFile(response: HttpServletResponse, id: Long)
    fun put(fileId: Long, fileRequest: FileRequest): File
}