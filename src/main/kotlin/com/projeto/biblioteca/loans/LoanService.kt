package com.projeto.biblioteca.loans
import LoanDeleteException
import LoanNotFoundException
import LoanSaveException
import LoanUpdateException
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class LoanService(
    private val loanRepository: LoanRepository,
) {

    fun findAll(sort: Sort): List<Loan> {
        log.info("Fetching all loans")
        return loanRepository.findAll(sort)
    }

    fun findById(id: Long): Loan? {
        log.info("Fetching loan with id: $id")
        return loanRepository.findById(id)
            .orElseThrow { LoanNotFoundException("Loan with id $id not found", null) }
    }

    fun save(loan: Loan): Loan {
        log.info("Saving loan: $loan")
        return try {
            loanRepository.save(loan)
        } catch (e: Exception) {
            log.error("Failed to save loan: $loan", e)
            throw LoanSaveException("Failed to save loan", e)
        }
    }

    fun update(loan: Loan): Loan {
        log.info("Updating loan: $loan")
        return try {
            loanRepository.save(loan)
        } catch (e: Exception) {
            log.error("Failed to update loan: $loan", e)
            throw LoanUpdateException("Failed to update loan", e)
        }
    }

    fun deleteById(id: Long) {
        log.info("Deleting loan with id: $id")
        return try {
            loanRepository.deleteById(id)
        } catch (e: Exception) {
            log.error("Failed to delete loan with id: $id", e)
            throw LoanDeleteException("Failed to delete loan with id $id", e)
        }
    }

    companion object {
        val log = LoggerFactory.getLogger(LoanService::class.java)
    }
}


