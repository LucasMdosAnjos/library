package com.projeto.biblioteca.books

data class BookResponse(
    val id: Long?,
    val title: String
) {
    constructor(book: Book) : this(
        id = book.id,
        title = book.title
    )
}
