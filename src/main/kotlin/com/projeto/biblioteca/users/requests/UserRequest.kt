package com.projeto.biblioteca.users.requests

import com.projeto.biblioteca.users.User
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRequest(
    @field:Email
    val email: String?,
    @field:NotBlank
    val password: String?,
    val name: String?,
){
    fun toUser(): User = User(
            email = email!!,
            password = password!!,
            name = name ?: ""
        )
}
