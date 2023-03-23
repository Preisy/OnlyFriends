package ru.onlyfriends.api.service.meService

import ru.onlyfriends.api.model.entity.User

interface MeService {
    fun showMe(): User
}