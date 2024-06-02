package com.projeto.biblioteca.loans

import jakarta.validation.constraints.NotBlank
import com.projeto.biblioteca.books.Book
import com.projeto.biblioteca.users.User
import java.time.LocalDate
import java.time.LocalDateTime

data class LoanRequest(
    @field:NotBlank
    val bookId: Long,
    @field:NotBlank
    val userId: Long
) {
    fun toLoan(book: Book, user: User): Loan = Loan(
        book = book,
        user = user,
        loanDate = LocalDateTime.now(),
        status = LoanStatus.ACTIVE
    )
}
