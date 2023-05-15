package ru.onlyfriends.api.controller.me

import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ru.onlyfriends.api.model.dto.request.FileRequest
import ru.onlyfriends.api.model.dto.request.UpdateUserRequest
import ru.onlyfriends.api.model.dto.request.UploadFileRequest
import ru.onlyfriends.api.service.meService.MeService

@RestController
@RequestMapping("/me")
class MeController(
    private val meService: MeService
) {
    @GetMapping
    fun showMe() = meService.showMe()

    @PostMapping("/picture")
    fun setPicture(@RequestBody file: FileRequest) = meService.setPicture(file)
    @PutMapping
    fun updateMe(
        @RequestBody user: UpdateUserRequest,
    ) = meService.updateMe(user)

    @PutMapping("/picture")
    fun updatePicture(
        @RequestPart("file") file: MultipartFile
    ) = meService.updateProfilePicture(UploadFileRequest(file))
}