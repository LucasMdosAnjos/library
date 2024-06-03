package com.projeto.biblioteca.books

import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.io.Console

@RestController
@RequestMapping("/books")
class BookController(private val bookService: BookService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<BookResponse>> {
        val books = bookService.findAll().map { BookResponse(it) }
        return ResponseEntity.ok(books)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<BookResponse> {
        val book = bookService.findById(id)
        return ResponseEntity.ok(BookResponse(book))
    }

    @PostMapping
    fun create(@RequestBody book: BookRequest): ResponseEntity<BookResponse> =
        bookService.save(book.toBook()).let {
            ResponseEntity.status(HttpStatus.CREATED).body(BookResponse(it))
        }


    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody book: BookRequest): ResponseEntity<BookResponse> {
        book.toBook().let {
            it.id = id
            val updatedBook = bookService.save(it)
            return ResponseEntity.ok(BookResponse(updatedBook))
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        bookService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}
