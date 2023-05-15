package ru.onlyfriends.api.service.meService

import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.dto.ApiResponse
import ru.onlyfriends.api.model.dto.exception.EmailAlreadyTakenException
import ru.onlyfriends.api.model.dto.request.FileRequest
import ru.onlyfriends.api.model.dto.request.UpdateUserRequest
import ru.onlyfriends.api.model.dto.request.UploadFileRequest
import ru.onlyfriends.api.model.dto.responses.MessageResponse
import ru.onlyfriends.api.model.entity.User
import ru.onlyfriends.api.model.repository.FileRepository
import ru.onlyfriends.api.model.repository.UserRepository
import ru.onlyfriends.api.service.fileService.FileService


@Service
class MeServiceImpl(
    val fileRepository: FileRepository,
    val fileService: FileService,
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

    override fun updateMe(userRequest: UpdateUserRequest): User {
        val me = getPrincipal()
            .updateEmail(userRequest.email)
            .updateNickname(userRequest.nickname)
        return userRepository.save(me)
    }

    private fun User.updateEmail(email: String?): User =
        this.apply {
            email?.let {
                userRepository.run {
                    if (!existsByEmail(email)) this@updateEmail.email = email else
                        throw EmailAlreadyTakenException()
                }
            }
        }


    private fun User.updateNickname(nickname: String?): User =
        this.also {
            nickname?.let {
                userRepository.run {
                    if (!existsByNickname(nickname)) this@updateNickname.nickname = nickname
                }
            }
        }

    override fun updateProfilePicture(fileRequest: UploadFileRequest): User {
        val fileEntity = fileService.create(fileRequest)
        setPicture(FileRequest(fileEntity.id))
        return getPrincipal()
    }
    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}