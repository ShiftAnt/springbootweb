package org.study.springbootweb.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.study.springbootweb.domain.posts.Posts
import org.study.springbootweb.domain.posts.PostsRepository
import org.study.springbootweb.web.dto.PostsSaveRequestDto
import org.study.springbootweb.web.dto.PostsUpdateRequestDto

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest @Autowired constructor(
    private val restTemplate: TestRestTemplate,
    private val postsRepository: PostsRepository
) {
    @LocalServerPort
    private var port: Int = 0

    @AfterEach
    fun teardown() {
        postsRepository.deleteAll()
    }

    @Test
    fun registeredPosts() {
        val title = "title"
        val content = "content"
        val requestDto = PostsSaveRequestDto(
            title = title,
            content = content,
            author = "author"
        )

        val url = "http://localhost:${port}/api/v1/posts"

        //when
        val responseEntity = restTemplate.postForEntity(url, requestDto, Long::class.java)

        //then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
    }

    @Test
    fun updatedPosts() {
        val savePosts = postsRepository.save(
            Posts(
                title = "title",
                content = "content",
                author = "author"
            )
        )

        val updateId = savePosts.id
        val expectedTitle = "title2"
        val expectedContent = "content2"

        val requestDto = PostsUpdateRequestDto(
            title = expectedTitle,
            content = expectedContent
        )

        val url = "http://localhost:${port}/api/v1/posts/$updateId"

        val requestEntity = HttpEntity(requestDto)

        //when
        val responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long::class.java)

        //then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0L)

        val all = postsRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }
}