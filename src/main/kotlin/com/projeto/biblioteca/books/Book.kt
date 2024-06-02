package com.projeto.biblioteca.books

import jakarta.persistence.*

@Entity
@Table(name = "tblBook")
class Book(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var title: String,
)
