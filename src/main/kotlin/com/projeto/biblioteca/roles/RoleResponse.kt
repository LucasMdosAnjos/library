package com.projeto.biblioteca.roles

import com.projeto.biblioteca.roles.Role

class RoleResponse(
    val name: String,
    val description: String,
){
    constructor(role: Role): this(name = role.name, description = role.description)
}