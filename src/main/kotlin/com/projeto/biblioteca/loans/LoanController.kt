package com.projeto.biblioteca.loans

import com.projeto.biblioteca.books.BookRepository
import com.projeto.biblioteca.users.UserRepository
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.web.bind.annotation.*

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize


@RestController
@RequestMapping("/loans")
class LoanController(
    private val loanService: LoanService,
    private val bookRepository: BookRepository,
    private val userRepository: UserRepository
) {

    @GetMapping
    fun findAll(@RequestParam sort: String?): ResponseEntity<List<LoanResponse>> {
        val sortDirection = if (sort?.lowercase() == "desc") Sort.Direction.DESC else Sort.Direction.ASC
        val loans = loanService.findAll(Sort.by(sortDirection, "loanDate")).map { LoanResponse(it) }
        return ResponseEntity.ok(loans)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<LoanResponse> {
        val loan = loanService.findById(id)
        return ResponseEntity.ok(LoanResponse(loan))
    }

    @PostMapping
    fun create(@RequestBody loanRequest: LoanRequest): ResponseEntity<LoanResponse> {
        val book = bookRepository.findByIdOrNull(loanRequest.bookId)?: return ResponseEntity.badRequest().build()
        val user = userRepository.findByIdOrNull(loanRequest.userId)?: return ResponseEntity.badRequest().build()
        val loan = loanRequest.toLoan(book, user)
        val savedLoan = loanService.save(loan)
        return ResponseEntity.status(HttpStatus.CREATED).body(LoanResponse(savedLoan))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody loan: Loan): ResponseEntity<LoanResponse> {
        loan.id = id
        val updatedLoan = loanService.update(loan)
        return ResponseEntity.ok(LoanResponse(updatedLoan))
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        loanService.deleteById(id)
        return ResponseEntity.noContent().build()
    }
}


