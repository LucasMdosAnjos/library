package com.projeto.biblioteca

import com.projeto.biblioteca.books.Book
import com.projeto.biblioteca.books.BookRepository
import com.projeto.biblioteca.roles.Role
import com.projeto.biblioteca.roles.RoleRepository
import com.projeto.biblioteca.users.User
import com.projeto.biblioteca.users.UserRepository
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class Bootstrapper(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val bookRepository: BookRepository,
): ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val adminRole =
            roleRepository.findByName("ADMIN")
                ?: roleRepository
                    .save(Role(name = "ADMIN", description = "System administrator"))
                    .also { roleRepository.save(Role(name = "USER", description = "Premium User")) }

        if(userRepository.findByRole(adminRole.name).isEmpty()){
            val admin = User(
                name = "Auth Server Administrator",
                email = "authserver.administrator@gmail.com",
                password = "admin"
            )
            admin.roles.add(adminRole)
            userRepository.save(admin)
        }

        val books: List<Book> = mutableListOf(
            Book(title = "Livro 01"),
            Book(title = "Livro 02"),
            Book(title = "Livro 03")
        )
        books.forEach { bookRepository.save(it) }
    }

}