package com.projeto.biblioteca.books
import BookNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

import io.mockk.*
import java.util.*

class BookServiceTest {
    private val mockBookRepository = mockk<BookRepository>()
    private val bookService = BookService(mockBookRepository)

    @BeforeEach fun setup() = clearAllMocks()
    @AfterEach fun cleanUp() = checkUnnecessaryStub()

    @Test
    fun `findAll should return all books`() {
        val books = listOf(Book(id = 1L, title = "Book One"), Book(id = 2L, title = "Book Two"))
        every { mockBookRepository.findAll() } returns books

        val result = bookService.findAll()

        assertEquals(books, result)
    }

    @Test
    fun `findById should return book if found`() {
        val book = Book(id = 1L, title = "Book One")
        every { mockBookRepository.findById(1L) } returns Optional.of(book)

        val result = bookService.findById(1L)

        assertEquals(book, result)
    }

    @Test
    fun `findById should throw exception if book not found`() {
        every { mockBookRepository.findById(1L) } returns Optional.empty()

        val exception = assertThrows<BookNotFoundException> {
            bookService.findById(1L)
        }

        assertEquals("Failed to find book with id 1", exception.message)
    }

    @Test
    fun `save should save book`() {
        val book = Book(id = 1L, title = "Book One")
        every { mockBookRepository.save(book) } returns book

        val result = bookService.save(book)

        assertEquals(book, result)
        verify { mockBookRepository.save(book) }
    }

    @Test
    fun `deleteById should delete book`() {
        every { mockBookRepository.deleteById(1L) } just Runs

        bookService.deleteById(1L)

        verify { mockBookRepository.deleteById(1L) }
    }
}