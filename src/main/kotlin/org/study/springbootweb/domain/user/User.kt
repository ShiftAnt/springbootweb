package org.study.springbootweb.domain.user

import org.study.springbootweb.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false)
    var email: String,

    @Column
    var picture: String?,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role
) : BaseTimeEntity() {
    fun update(name: String, picture: String?): User {
        this.name = name
        this.picture = picture

        return this
    }
}