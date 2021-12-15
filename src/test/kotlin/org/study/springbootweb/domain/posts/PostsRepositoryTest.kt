package org.study.springbootweb.domain.posts

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class PostsRepositoryTest @Autowired constructor(
    private val postsRepository: PostsRepository
) {
    @AfterEach
    fun cleanUp() {
        postsRepository.deleteAll()
    }

    @Test
    fun postsSaveAndLoad() {
        val title = "테스트 게시글"
        val content = "테스트 본문"

        val pos = Posts(0, title, content, "temp@gmail.com")
        postsRepository.save(pos)

        //when
        val postsList = postsRepository.findAll()

        //then
        val posts = postsList[0]
        assertThat(posts.title).isEqualTo(title)
        assertThat(posts.content).isEqualTo(content)
    }

    @Test
    fun registerBaseTimeEntity() {
        val now = LocalDateTime.of(2021, 12, 10, 0, 0, 0)
        postsRepository.save(
            Posts(
                title = "title",
                content = "content",
                author = "author"
            )
        )

        //when
        val postsList = postsRepository.findAll()

        val posts = postsList[0]

        println(">>>>>>>>> createDate=${posts.createDate}, modifiedDate=${posts.modifiedDate}")

        assertThat(posts.createDate).isAfter(now)
        assertThat(posts.modifiedDate).isAfter(now)
    }
}