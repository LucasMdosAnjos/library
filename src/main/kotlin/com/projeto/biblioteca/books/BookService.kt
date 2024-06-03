package com.projeto.biblioteca.books

import BookNotFoundException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class BookService(private val bookRepository: BookRepository) {

    fun findAll(): List<Book> {
        log.info("Fetching all books")
        return bookRepository.findAll()
    }

    fun findById(id: Long): Book {
        log.info("Fetching book with id: $id")
        return try {
            bookRepository.findById(id).orElseThrow { throw BookNotFoundException("Book with id $id not found", null) }
        } catch (e: Exception) {
            throw BookNotFoundException("Failed to find book with id $id", e)
        }
    }

    fun save(book: Book): Book {
        log.info("Saving book: $book")
        return try {
            bookRepository.save(book)
        } catch (e: Exception) {
            log.error("Failed to save book: $book", e)
            throw BookNotFoundException("Failed to save book", e)
        }
    }

    fun deleteById(id: Long) {
        log.info("Deleting book with id: $id")
        return try {
            bookRepository.deleteById(id)
        } catch (e: Exception) {
            throw BookNotFoundException("Failed to delete book", e)
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(BookService::class.java)
    }
}
