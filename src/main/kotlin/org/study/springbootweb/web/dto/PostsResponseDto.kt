package org.study.springbootweb.web.dto

import org.study.springbootweb.domain.posts.Posts

data class PostsResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: String
) {
    constructor(entity: Posts) : this(entity.id, entity.title, entity.content, entity.author)
}