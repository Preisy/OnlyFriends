package ru.onlyfriends.api.service.fileService

import jakarta.servlet.http.HttpServletResponse
import ru.onlyfriends.api.model.dto.request.UploadFileRequest
import ru.onlyfriends.api.model.dto.request.WithFilesRequest
import ru.onlyfriends.api.model.entity.files.File
import ru.onlyfriends.api.model.repository.FileRepository

interface FileService {
    val repository: FileRepository
    fun canUserModeratePost(fileId: Long): Boolean
    fun getFile(response: HttpServletResponse, id: Long)
    fun put(fileId: Long, uploadFileRequest: UploadFileRequest): File
    fun delete(id: Long)
    fun create(request: UploadFileRequest): File

    fun fillWithFiles(request: WithFilesRequest)

}