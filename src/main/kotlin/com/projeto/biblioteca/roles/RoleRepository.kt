package com.projeto.biblioteca.roles

import com.projeto.biblioteca.roles.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long>  {
    fun findByName(name: String): Role?
}