package com.projeto.biblioteca.roles

import jakarta.persistence.*

@Entity
@Table(name = "tblRole")
class Role (
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false)
    var name: String,

    var description: String = "",

)