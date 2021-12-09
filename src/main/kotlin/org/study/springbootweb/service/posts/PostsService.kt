package org.study.springbootweb.service.posts

import org.springframework.stereotype.Service
import org.study.springbootweb.domain.posts.PostsRepository
import org.study.springbootweb.web.dto.PostsResponseDto
import org.study.springbootweb.web.dto.PostsSaveRequestDto
import org.study.springbootweb.web.dto.PostsUpdateRequestDto
import javax.transaction.Transactional

@Service
class PostsService(
    private val postsRepository: PostsRepository
) {
    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long {
        return postsRepository.save(requestDto.toEntity()).id
    }

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long {
        val posts = postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id= $id") }

        posts.update(requestDto.title, requestDto.content)

        return id
    }

    fun findById(id: Long): PostsResponseDto {
        val entity = postsRepository.findById(id).orElseThrow { IllegalArgumentException("해당 게시글이 없습니다. id= $id") }

        return PostsResponseDto(entity)
    }
}