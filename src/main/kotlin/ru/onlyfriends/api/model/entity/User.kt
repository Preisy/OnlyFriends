package ru.onlyfriends.api.model.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity
@JsonIgnoreProperties("upassword", "password")
class User(
    @Column(length = 255, nullable = false)
    var email: String,
    @Column(name = "password", length = 255, nullable = false)
    var uPassword: String,
    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    val roles: MutableSet<UserRole> = mutableSetOf(UserRole(UserRoleType.User))
) : AbstractEntity(), UserDetails {
    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val roles: Set<UserRole> = roles
        val authorities: MutableList<SimpleGrantedAuthority> = ArrayList()
        for (role in roles) {
            authorities.add(SimpleGrantedAuthority("ROLE_${role.role}"))
        }

        return authorities
    }

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