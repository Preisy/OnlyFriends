package ru.onlyfriends.api.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.onlyfriends.api.model.dto.request.UploadFileRequest
import ru.onlyfriends.api.service.fileService.FileService

@RestController
@RequestMapping("/files")
class FilesController (
    private val filesService: FileService
) {
    @GetMapping("/{id}")
    fun getImage(response: HttpServletResponse,
                 @PathVariable id: Long) = filesService.getFile(response, id)
    @PostMapping()
    fun uploadFile(@RequestPart("file") file: MultipartFile) = filesService.create(UploadFileRequest(file))

    @DeleteMapping("/{id}")
    @PreAuthorize("@PostService.canUserModeratePost(#id) || hasRole('MODERATOR')")
    fun delete(@PathVariable id: Long) = filesService.delete(id)

}