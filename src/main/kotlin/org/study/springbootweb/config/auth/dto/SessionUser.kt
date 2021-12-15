package org.study.springbootweb.config.auth.dto

import org.study.springbootweb.domain.user.User
import java.io.Serializable

data class SessionUser(
    val name: String,
    val email: String,
    val picture: String?
): Serializable {
    constructor(user: User) : this(
        name = user.name,
        email = user.email,
        picture = user.picture
    )
}