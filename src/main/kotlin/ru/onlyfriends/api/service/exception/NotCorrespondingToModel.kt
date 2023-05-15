package ru.onlyfriends.api.service.exception

class NotCorrespondingToModel(
    override val message: String? = null
) : Exception(message ?: "Not an instance of model")