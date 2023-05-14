package ru.onlyfriends.api.model.dto.request

import ru.onlyfriends.api.model.entity.files.File

interface WithFilesRequest {
    val filesIds: List<Long>
    var files: List<File>
}