package com.example.onlyfriends.model.dtos

import java.beans.ConstructorProperties

data class LoginDto
@ConstructorProperties("login", "password")
constructor(val login: String, val password: String)