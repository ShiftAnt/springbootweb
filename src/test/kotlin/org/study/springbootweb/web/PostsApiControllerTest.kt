package org.study.springbootweb.web

import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.put
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.study.springbootweb.domain.posts.Posts
import org.study.springbootweb.domain.posts.PostsRepository
import org.study.springbootweb.web.dto.PostsSaveRequestDto
import org.study.springbootweb.web.dto.PostsUpdateRequestDto

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest @Autowired constructor(
    private val postsRepository: PostsRepository,
    private val context: WebApplicationContext,
) {
    @LocalServerPort
    private var port: Int = 0

    private lateinit var mvc: MockMvc

    @BeforeEach
    fun setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply { }
            .build()
    }

    @AfterEach
    fun teardown() {
        postsRepository.deleteAll()
    }

    @Test
    @WithMockUser(roles = ["USER"])
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
        mvc.post(url) {
            this.contentType = MediaType.APPLICATION_JSON
            this.content = ObjectMapper().writeValueAsString(requestDto)
        }.andExpect { status { isOk() } }

        //then
        val all = postsRepository.findAll()
        assertThat(all.first().title).isEqualTo(title)
        assertThat(all.first().content).isEqualTo(content)
    }

    @Test
    @WithMockUser(roles = ["USER"])
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

        //when
        mvc.put(url) {
            this.contentType = MediaType.APPLICATION_JSON
            this.content = ObjectMapper().writeValueAsString(requestDto)
        }.andExpect { status { isOk() } }

        //then
        val all = postsRepository.findAll()
        assertThat(all.first().title).isEqualTo(expectedTitle)
        assertThat(all.first().content).isEqualTo(expectedContent)
    }
}