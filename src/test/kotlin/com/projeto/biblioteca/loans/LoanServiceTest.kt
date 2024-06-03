package com.projeto.biblioteca.loans

import LoanNotFoundException
import io.mockk.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.Sort
import java.time.LocalDateTime
import java.util.*

class LoanServiceTest {

    private val loanRepository = mockk<LoanRepository>()
    private val loanService = LoanService(loanRepository)

    @Test
    fun `findAll should return all loans`() {
        val loans = listOf(
            Loan(id = 1L, book = mockk(), user = mockk(), loanDate = LocalDateTime.now(), status = LoanStatus.ACTIVE),
            Loan(id = 2L, book = mockk(), user = mockk(), loanDate = LocalDateTime.now(), status = LoanStatus.ACTIVE)
        )
        every { loanRepository.findAll(any<Sort>()) } returns loans

        val result = loanService.findAll(Sort.by("loanDate"))

        assertEquals(loans, result)
    }

    @Test
    fun `findById should return loan if found`() {
        val loan = Loan(id = 1L, book = mockk(), user = mockk(), loanDate = LocalDateTime.now(), status = LoanStatus.ACTIVE)
        every { loanRepository.findById(1L) } returns Optional.of(loan)

        val result = loanService.findById(1L)

        assertEquals(loan, result)
    }

    @Test
    fun `findById should throw exception if loan not found`() {
        val loan = Loan(id = 1L, book = mockk(), user = mockk(), loanDate = LocalDateTime.now(), status = LoanStatus.ACTIVE)

        every { loanRepository.findById(1L) } returns Optional.empty()

        val exception = assertThrows<LoanNotFoundException> {
            loanService.findById(1L)
        }

        assertEquals("Loan with id 1 not found", exception.message)
    }

    @Test
    fun `save should save loan`() {
        val loan = Loan(id = 1L, book = mockk(), user = mockk(), loanDate = LocalDateTime.now(), status = LoanStatus.ACTIVE)
        every { loanRepository.save(loan) } returns loan

        val result = loanService.save(loan)

        assertEquals(loan, result)
        verify { loanRepository.save(loan) }
    }

    @Test
    fun `update should update loan`() {
        val loan = Loan(id = 1L, book = mockk(), user = mockk(), loanDate = LocalDateTime.now(), status = LoanStatus.ACTIVE)
        every { loanRepository.save(loan) } returns loan

        val result = loanService.update(loan)

        assertEquals(loan, result)
        verify { loanRepository.save(loan) }
    }

    @Test
    fun `deleteById should delete loan`() {
        every { loanRepository.deleteById(1L) } just Runs

        loanService.deleteById(1L)

        verify { loanRepository.deleteById(1L) }
    }
}
