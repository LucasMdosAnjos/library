package com.projeto.biblioteca.roles

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/roles")
class RoleController(val roleService: RoleService) {
    @PostMapping
    fun insert(@RequestBody @Valid role: RoleRequest) {
        role.toRole()
            .let { roleService.insert(it) }
            .let { RoleResponse(it) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }

    @GetMapping
    fun list() = roleService.findAll()
        .map { RoleResponse(it) }
        .let { ResponseEntity.ok(it) }
}