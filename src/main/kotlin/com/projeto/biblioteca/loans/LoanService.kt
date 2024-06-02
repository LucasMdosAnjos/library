package com.projeto.biblioteca.loans
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class LoanService(
    private val loanRepository: LoanRepository,
) {

    fun findAll(sort: Sort): List<Loan> = loanRepository.findAll(sort)

    fun findById(id: Long): Loan = loanRepository.findById(id).orElseThrow { RuntimeException("Loan not found") }

    fun save(loan: Loan): Loan = loanRepository.save(loan)

    fun update(loan: Loan): Loan = loanRepository.save(loan)

    fun deleteById(id: Long) = loanRepository.deleteById(id)
}


