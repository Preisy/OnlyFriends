package ru.onlyfriends.api.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
class User(
    @Column(length = 255, nullable = false)
    var email: String,
    @Column(length = 255, nullable = false)
    var uPassword: String
) : AbstractEntity(), UserDetails {
    override fun getAuthorities() = emptyList<GrantedAuthority>()
    override fun getPassword() = uPassword
    override fun getUsername() = email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}