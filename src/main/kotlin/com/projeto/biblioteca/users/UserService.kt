package com.projeto.biblioteca.users

import com.projeto.biblioteca.roles.RoleRepository
import com.projeto.biblioteca.security.Jwt
import com.projeto.biblioteca.users.responses.LoginResponse
import com.projeto.biblioteca.users.responses.UserResponse
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.security.auth.login.LoginException

// Definição das classes de exceção
class UserSaveException(message: String?, cause: Throwable?) : RuntimeException(message, cause)
class UserNotFoundException(message: String?, cause: Throwable?) : RuntimeException(message, cause)
class UserDeleteException(message: String?, cause: Throwable?) : RuntimeException(message, cause)
class UserGrantException(message: String?, cause: Throwable?) : RuntimeException(message, cause)
class UserLoginException(message: String?, cause: Throwable?) : RuntimeException(message, cause)
class InvalidPasswordException(message: String?, cause: Throwable?) : RuntimeException(message, cause)

@Service
class UserService(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val jwt: Jwt
) {
    fun save(user: User): User =
        userRepository.save(user)

    fun findAll(dir: SortDir, role: String?): List<User> =
        role?.let { r ->
            when (dir) {
                SortDir.ASC -> userRepository.findByRole(r.uppercase()).sortedBy { it.name }
                SortDir.DESC -> userRepository.findByRole(r.uppercase()).sortedByDescending { it.name }
            }
        } ?: when (dir) {
            SortDir.ASC -> userRepository.findAll(Sort.by("name").ascending())
            SortDir.DESC -> userRepository.findAll(Sort.by("name").descending())
        }


    fun findByIdOrNull(id: Long) =
        userRepository.findByIdOrNull(id)

    fun delete(id: Long) {
        //userRepository.findByIdOrNull(id) ?: throw NotFoundException("User not found!", null)
        userRepository.deleteById(id)
    }

    fun grant(id: Long, roleName: String): Boolean {
        val user = userRepository.findByIdOrNull(id) ?: throw IllegalArgumentException("User $id not found!")

        if (user.roles.any { it.name == roleName }) return false

        val role = roleRepository.findByName(roleName) ?: throw IllegalArgumentException("Invalid role $roleName!")

        user.roles.add(role)
        userRepository.save(user)
        return true
    }

    fun login(email: String, password: String): LoginResponse? {
        val user = userRepository.findByEmail(email)
        if(user == null){
            log.warn("User {} not found", email)
            throw UserNotFoundException("User $email not found!", null)
            }
        if (password != user.password){
            log.warn("Invalid password!", email)
            throw InvalidPasswordException("Invalid password!", null)
        }
        log.info("User logged in: id={}, name={}", user.id,user.name)
        return LoginResponse(
            token = jwt.createToken(user),
            user = UserResponse(user)
        )
    }

    companion object {
        val log = LoggerFactory.getLogger(UserService::class.java)!!
    }
}