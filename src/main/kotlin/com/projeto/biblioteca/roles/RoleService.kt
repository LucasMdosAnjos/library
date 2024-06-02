package com.projeto.biblioteca.roles

import com.projeto.biblioteca.roles.Role
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class RoleService(val roleRepository: RoleRepository) {
    fun insert(role: Role) =
        roleRepository.save(role)

    fun findAll(): List<Role> =
        roleRepository.findAll(Sort.by("name").ascending())

}