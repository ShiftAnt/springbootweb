package org.study.springbootweb.web

import org.springframework.web.bind.annotation.*
import org.study.springbootweb.service.posts.PostsService
import org.study.springbootweb.web.dto.PostsResponseDto
import org.study.springbootweb.web.dto.PostsSaveRequestDto
import org.study.springbootweb.web.dto.PostsUpdateRequestDto

@RestController
class PostsApiController (
    private val postsService: PostsService
) {
    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long {
        return postsService.save(requestDto)
    }

    @PutMapping("/api/v1/posts/{id}")
    fun update(@PathVariable id: Long,
               @RequestBody requestDto: PostsUpdateRequestDto): Long {
        return postsService.update(id, requestDto)
    }

    @GetMapping("/api/v1/posts/{id}")
    fun findById(@PathVariable id: Long): PostsResponseDto {
        return postsService.findById(id)
    }
}