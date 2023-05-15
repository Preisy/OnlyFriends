package ru.onlyfriends.api.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class UserRole(
    @Column
    var name: UserRoleType = UserRoleType.USER
) : AbstractEntity()