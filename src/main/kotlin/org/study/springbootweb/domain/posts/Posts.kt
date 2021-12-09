package org.study.springbootweb.domain.posts

import org.study.springbootweb.domain.BaseTimeEntity
import javax.persistence.*

@Entity
class Posts (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(length = 500, nullable = false)
    var title: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String,

    var author: String
) : BaseTimeEntity() {
    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}