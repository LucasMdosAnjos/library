package com.projeto.biblioteca.loans

import org.springframework.data.jpa.repository.JpaRepository

interface LoanRepository : JpaRepository<Loan, Long>
