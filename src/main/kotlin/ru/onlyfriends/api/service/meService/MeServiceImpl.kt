package ru.onlyfriends.api.service.meService

import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.request.FileRequest
import ru.onlyfriends.api.model.dto.responses.MessageResponse
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.FileRepository
import ru.onlyfriends.api.model.repository.UserRepository


@Service
class MeServiceImpl(
    val fileRepository: FileRepository,
    val userRepository: UserRepository
) : MeService {
    override fun showMe(): User = getPrincipal()
    override fun setPicture(fileRequest: FileRequest): ApiResponse =
        fileRepository.getFileById(fileRequest.id)?.let { file ->
            val user = getPrincipal()
            user.file = file
            userRepository.save(user)
            MessageResponse("File added")
        } ?: MessageResponse("No file with such id", HttpStatus.NOT_FOUND)



    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}