package org.study.springbootweb.web.dto

import org.study.springbootweb.domain.posts.Posts

data class PostsSaveRequestDto(
    val title: String,
    val content: String,
    val author: String
) {
    fun toEntity() = Posts(
            title = this.title,
            content = this.content,
            author = this.author
        )

}