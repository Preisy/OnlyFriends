package ru.onlyfriends.api.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.Column
import jakarta.persistence.Entity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@JsonIgnoreProperties("upassword", "password")
class User(
    @Column(length = 255, nullable = false)
    var email: String,
    @Column(length = 255, nullable = false)
    var uPassword: String
) : AbstractEntity(), UserDetails {
    @JsonIgnore
    override fun getAuthorities() = emptyList<GrantedAuthority>()

    @JsonIgnore
    override fun getPassword() = uPassword

    @JsonIgnore
    override fun getUsername() = email

    @JsonIgnore
    override fun isAccountNonExpired() = true

    @JsonIgnore
    override fun isAccountNonLocked() = true

    @JsonIgnore
    override fun isCredentialsNonExpired() = true

    @JsonIgnore
    override fun isEnabled() = true
}