package ru.onlyfriends.api.configuration.security

import ru.onlyfriends.api.model.entity.UserRoleType

class RoleHierarchy {
    companion object {
        val hierarchyList: List<UserRoleType> = listOf(UserRoleType.ADMIN, UserRoleType.MODERATOR, UserRoleType.USER)
        val hierarchyString: String = "ROLE_ADMIN > ROLE_MODERATOR > ROLE_USER";
    }
}