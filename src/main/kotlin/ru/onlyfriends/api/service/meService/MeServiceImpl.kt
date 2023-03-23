package ru.onlyfriends.api.service.meService

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import ru.onlyfriends.api.model.entity.User

@Service
class MeServiceImpl : MeService {
    override fun showMe(): User = getPrincipal()


    private fun getPrincipal(): User = SecurityContextHolder.getContext().authentication.principal as User

}