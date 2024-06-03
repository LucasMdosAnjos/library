package com.projeto.biblioteca.loans
import LoanDeleteException
import LoanNotFoundException
import LoanSaveException
import LoanUpdateException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class LoanExceptionHandler {

    private fun generateHtml(title: String, message: String?, details: String): String {
        return """
            <!DOCTYPE html>
            <html>
            <head>
                <title>$title</title>
                <style>
                    body {
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        height: 100vh;
                        margin: 0;
                        font-family: Arial, sans-serif;
                        background-color: #f0f0f0;
                    }
                    .container {
                        text-align: center;
                        background: white;
                        padding: 20px;
                        border-radius: 10px;
                        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                    }
                    h1 {
                        color: #333;
                    }
                    p {
                        color: #666;
                    }
                </style>
            </head>
            <body>
                <div class="container">
                    <h1>$title</h1>
                    <p>$message</p>
                    <p><i>$details</i></p>
                </div>
            </body>
            </html>
        """.trimIndent()
    }

    @ExceptionHandler(LoanNotFoundException::class)
    fun handleLoanNotFoundException(ex: LoanNotFoundException, request: WebRequest): ResponseEntity<String> {
        val htmlResponse = generateHtml("Loan Not Found", ex.message, request.getDescription(false))
        return ResponseEntity(htmlResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(LoanSaveException::class)
    fun handleLoanSaveException(ex: LoanSaveException, request: WebRequest): ResponseEntity<String> {
        val htmlResponse = generateHtml("Loan Save Error", ex.message, request.getDescription(false))
        return ResponseEntity(htmlResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(LoanUpdateException::class)
    fun handleLoanUpdateException(ex: LoanUpdateException, request: WebRequest): ResponseEntity<String> {
        val htmlResponse = generateHtml("Loan Update Error", ex.message, request.getDescription(false))
        return ResponseEntity(htmlResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(LoanDeleteException::class)
    fun handleLoanDeleteException(ex: LoanDeleteException, request: WebRequest): ResponseEntity<String> {
        val htmlResponse = generateHtml("Loan Delete Error", ex.message, request.getDescription(false))
        return ResponseEntity(htmlResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
