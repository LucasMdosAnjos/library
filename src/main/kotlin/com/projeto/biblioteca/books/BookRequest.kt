package com.projeto.biblioteca.books

import jakarta.validation.constraints.NotBlank


data class BookRequest(
    @field:NotBlank
    var title: String,
){
    fun toBook(): Book = Book(
        title = title
    )
}
