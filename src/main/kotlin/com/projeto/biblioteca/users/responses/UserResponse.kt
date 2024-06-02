package com.projeto.biblioteca.users.responses

import com.projeto.biblioteca.users.User

data class UserResponse(
    val id: Long,
    val name: String,
    val email: String,
){
    constructor(u: User) : this(id = u.id!!, name = u.name, email = u.email)
}
