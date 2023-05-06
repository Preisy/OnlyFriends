package ru.onlyfriends.api.model.repository

import org.springframework.data.repository.CrudRepository
import ru.onlyfriends.api.model.entity.File

interface FileRepository : CrudRepository<File, Long> {
    fun getFileById(id: Long): File?
}