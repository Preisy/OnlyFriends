package ru.onlyfriends.api.service.meService

import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.request.FileRequest
import ru.onlyfriends.api.model.entity.User

interface MeService {
    fun showMe(): User

    fun setPicture(fileRequest: FileRequest): ApiResponse

}