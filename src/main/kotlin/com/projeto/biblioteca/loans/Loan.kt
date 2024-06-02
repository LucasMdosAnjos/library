package com.projeto.biblioteca.loans

import com.projeto.biblioteca.books.Book
import com.projeto.biblioteca.users.User
import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "tblLoan")
class Loan(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne
    @JoinColumn(name = "book_id")
    var book: Book,

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User,

    @NotNull
    var loanDate: LocalDateTime,

    var returnDate: LocalDateTime? = null,

    @Enumerated(EnumType.STRING)
    var status: LoanStatus
)

enum class LoanStatus {
    ACTIVE, RETURNED
}
