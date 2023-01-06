package com.example.onlyfriends.model.table

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.core.userdetails.UserDetails

@Entity
class User (
    @Column(nullable = false, length = 60)
    @JsonIgnore
    var password: String,
    @Column(nullable = false, unique = true, length = 60)
    var login: String
) : AbstractEntity()

