package org.study.springbootweb.web.dto

import org.study.springbootweb.domain.posts.Posts
import java.time.LocalDateTime

data class PostsListResponseDto(
    val id: Long,
    val title: String,
    val author: String,
    val modifiedDate: LocalDateTime
) {
    constructor(entity: Posts) : this(
        id = entity.id,
        title = entity.title,
        author = entity.author,
        modifiedDate = entity.modifiedDate
    )
}