package com.projeto.biblioteca.users.responses

data class LoginResponse (
    val token: String,
    val user: UserResponse
)