package ru.onlyfriends.api.service.fileService

import jakarta.servlet.http.HttpServletResponse
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ru.onlyfriends.api.model.dto.exception.NoFileException
import ru.onlyfriends.api.model.dto.exception.ResourceNotFoundException
import ru.onlyfriends.api.model.dto.request.UploadFileRequest
import ru.onlyfriends.api.model.dto.request.WithFilesRequest
import ru.onlyfriends.api.model.entity.files.File
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.FileRepository
import java.io.FileInputStream
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.StandardCopyOption

@Service
class FileServiceImpl(
    override val repository: FileRepository
) : FileService {

    override fun canUserModeratePost(fileId: Long): Boolean {
        return try {
            val user = getPrincipal()
            val post = repository.findById(fileId).get()
            post.author.id == user.id
        } catch (e: ResourceNotFoundException) {
            false
        }
    }

    override fun put(fileId: Long, uploadFileRequest: UploadFileRequest): File {
        val fileEntity = repository.getFileById(fileId) ?: throw NoFileException()
        fileEntity.type = uploadFileRequest.getExtension()
        saveFile(fileEntity, uploadFileRequest.file)
        return fileEntity
    }

    override fun getFile(response: HttpServletResponse, id: Long) {
        val file = repository.getFileById(id) ?: throw NoFileException()
        val path = file.getPath()
        val inputStream: InputStream = FileInputStream(path.toString())
        IOUtils.copy(inputStream, response.outputStream)
        response.flushBuffer()
    }

    override fun delete(id: Long) {
        val file = repository.getFileById(id) ?: throw NoFileException()
        repository.deleteById(id)
        Files.delete(file.getPath())
    }

    private fun saveFile(fileEntity: File, file: MultipartFile) {
        Files.copy(file.inputStream, fileEntity.getPath(), StandardCopyOption.REPLACE_EXISTING)
    }

    override fun create(request: UploadFileRequest): File {
        val user = getPrincipal()
        request.author = user
        val fileAsModel = request.asModel()
        val fileModel = repository.save(fileAsModel) // to set id
        saveFile(fileModel, request.file)
        return fileModel
    }

    override fun fillWithFiles(request: WithFilesRequest) {
        val files = mutableListOf<File>()
        request.filesIds.forEach { id ->
            repository.getFileById(id)?.let { files += it }
        }
        request.files = files
    }

    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}