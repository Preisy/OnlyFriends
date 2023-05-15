package ru.onlyfriends.api.service.meService

import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.request.FileRequest
import ru.onlyfriends.api.model.dto.request.UpdateUserRequest
import ru.onlyfriends.api.model.dto.request.UploadFileRequest
import ru.onlyfriends.api.model.entity.User

interface MeService {
    fun showMe(): User
    fun setPicture(fileRequest: FileRequest): ApiResponse
    fun updateMe(userRequest: UpdateUserRequest): User
    fun updateProfilePicture(fileRequest: UploadFileRequest): User

}