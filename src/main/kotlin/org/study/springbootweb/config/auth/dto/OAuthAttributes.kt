package org.study.springbootweb.config.auth.dto

import org.springframework.security.oauth2.core.OAuth2AuthorizationException
import org.springframework.security.oauth2.core.OAuth2Error
import org.study.springbootweb.domain.user.Role
import org.study.springbootweb.domain.user.User

data class OAuthAttributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String?
) {
    companion object {
        @JvmStatic
        fun of(
            registrationId: String,
            userNameAttributeName: String,
            attributes: Map<String, Any>
        ): OAuthAttributes {
            return when (registrationId.lowercase()) {
                "naver" -> ofNaver("id", attributes)
                "google" -> ofGoogle(userNameAttributeName, attributes)

                else -> throw OAuth2AuthorizationException(OAuth2Error(""))
            }
        }

        @JvmStatic
        fun ofGoogle(
            userNameAttributeName: String,
            attributes: Map<String, Any>
        ) = OAuthAttributes(
            name = attributes["name"] as String,
            email = attributes["email"] as String,
            picture = attributes["picture"] as String,
            attributes = attributes,
            nameAttributeKey = userNameAttributeName
        )

        @JvmStatic
        fun ofNaver(
            userNameAttributeName: String,
            attributes: Map<String, Any>,
        ) : OAuthAttributes {
            val response = attributes["response"] as Map<String, Any>

            return OAuthAttributes(
                name = response["name"] as String,
                email = response["email"] as String,
                picture = response["profile_image"] as String,
                attributes = response,
                nameAttributeKey = userNameAttributeName
            )
        }
    }

    fun toEntity() = User(
        name = name,
        email = email,
        picture = picture,
        role = Role.GUEST,
    )
}