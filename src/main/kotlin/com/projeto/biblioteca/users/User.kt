package com.projeto.biblioteca.users

import com.projeto.biblioteca.roles.Role
import com.fasterxml.jackson.annotation.JsonIgnore
import com.projeto.biblioteca.loans.Loan
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "tblUser")
class User(
    @Id @GeneratedValue
    var id: Long? = null,
    @Column(unique = true, nullable = false)
    var email: String = "",
    @NotNull
    var password: String = "",
    @NotNull
    var name: String = "",

    @ManyToMany
    @JoinTable(
        name = "UserRole",
        joinColumns = [JoinColumn(name = "idUser")],
        inverseJoinColumns = [JoinColumn(name = "idRole")]
    )
    @JsonIgnore
    val roles: MutableSet<Role> = mutableSetOf(),

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL])
    var loans: MutableList<Loan> = mutableListOf()
)