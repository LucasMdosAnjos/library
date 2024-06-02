package com.projeto.biblioteca.loans

import java.time.LocalDate
import java.time.LocalDateTime

data class LoanResponse(
    val id: Long?,
    val bookId: Long,
    val userId: Long,
    val loanDate: LocalDateTime,
    val returnDate: LocalDateTime?,
    val status: LoanStatus
) {
    constructor(loan: Loan) : this(
        id = loan.id,
        bookId = loan.book.id!!,
        userId = loan.user.id!!,
        loanDate = loan.loanDate,
        returnDate = loan.returnDate,
        status = loan.status
    )
}
