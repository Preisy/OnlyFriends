package ru.onlyfriends.api.configuration.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl


//@Configuration
//class CustomRoleHierarchy : RoleHierarchyImpl() {
//    @Bean
//    fun roleHierarchy(): RoleHierarchyImpl {
//        val roleHierarchy = RoleHierarchyImpl()
//        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MODERATOR > ROLE_USER > ROLE_GUEST")
//        return roleHierarchy
//    }
//}
@Configuration
class RoleConfig {
    @Bean
    fun roleHierarchy(): RoleHierarchyImpl {
        val roleHierarchy = RoleHierarchyImpl()
        roleHierarchy.setHierarchy(RoleHierarchy.hierarchyString)
        return roleHierarchy
    }

    @Bean
    fun expressionHandler(
        @Autowired
        roleHierarchyImpl: RoleHierarchyImpl
    ): DefaultMethodSecurityExpressionHandler {
        val expressionHandler = DefaultMethodSecurityExpressionHandler()
        expressionHandler.setRoleHierarchy(roleHierarchyImpl)
        return expressionHandler
    }
}