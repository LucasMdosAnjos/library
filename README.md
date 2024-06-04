# Library Management System

This project is a Library Management System built with Spring Boot and Kotlin. It provides a way to manage books, users, and loan records within a library.

## Project Members

This project was made in collaboration with Lucas Monteiro dos Anjos and Natan Bosso.

## Video Showcase

- [Video](https://youtu.be/EriFcsU29qE)

## Features

- Manage Books: Create, update, delete, and retrieve book records.
- Manage Users: Create, update, delete, and retrieve user records.
- Manage Loans: Create, update, delete, and retrieve loan records, associating books with users.
- Loan Management: Handle the loaning process, including sorting loan records by date.

## Technologies Used

- Spring Boot
- Kotlin
- Spring Data JPA
- Jakarta Persistence and Validation
- Hibernate
- PostgreSQL (or any preferred database)
- REST API

## Project Structure

- `Book`: Entity representing a book in the library.
- `User`: Entity representing a user of the library.
- `Loan`: Entity representing a loan record.
- `BookRequest`, `LoanRequest`: Data transfer objects for creating and updating books and loans.
- `BookResponse`, `LoanResponse`: Data transfer objects for responding with book and loan data.
- `BookController`, `LoanController`: REST controllers for handling HTTP requests.
- `BookService`, `LoanService`: Services for business logic.
- `BookRepository`, `LoanRepository`, `UserRepository`: Repositories for data access.

## API Endpoints

### Books

- `GET /api/books`: Retrieve all books.
- `GET /api/books/{id}`: Retrieve a book by ID.
- `POST /api/books`: Create a new book.
- `PUT /api/books/{id}`: Update an existing book.
- `DELETE /api/books/{id}`: Delete a book.

### Users

- `GET /api/users`: Retrieve all users.
- `GET /api/users/{id}`: Retrieve a user by ID.
- `POST /api/users`: Create a new user.
- `PUT /api/users/{id}`: Update an existing user.
- `DELETE /api/users/{id}`: Delete a user.

### Loans

- `GET /api/loans`: Retrieve all loans, with optional sorting by `loanDate`.
- `GET /api/loans/{id}`: Retrieve a loan by ID.
- `POST /api/loans`: Create a new loan.
- `PUT /api/loans/{id}`: Update an existing loan.
- `DELETE /api/loans/{id}`: Delete a loan.

## Usage

1. Clone the repository:

```sh
git clone https://github.com/LucasMdosAnjos/library.git
