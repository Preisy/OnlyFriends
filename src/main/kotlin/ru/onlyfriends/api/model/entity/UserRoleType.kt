package ru.onlyfriends.api.model.entity

enum class UserRoleType {
    ADMIN,
    MODERATOR,
    USER;

    override fun toString(): String {
        return super.toString().uppercase()
    }

//    @Getter
//    @Component("UserRoleType")
//    class SpringComponent {
//        val ADMIN = UserRoleType.ADMIN
//        val MODERATOR = UserRoleType.MODERATOR
//        val USER = UserRoleType.USER
//    }
}