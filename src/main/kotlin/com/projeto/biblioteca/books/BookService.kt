package com.projeto.biblioteca.books

import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun findAll(): List<Book> = bookRepository.findAll()

    fun findById(id: Long): Book = bookRepository.findById(id).orElseThrow { RuntimeException("Book not found") }

    fun save(book: Book): Book = bookRepository.save(book)

    fun deleteById(id: Long) = bookRepository.deleteById(id)
}
