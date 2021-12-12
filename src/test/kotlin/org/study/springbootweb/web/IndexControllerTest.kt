package org.study.springbootweb.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = RANDOM_PORT)
class IndexControllerTest @Autowired constructor(
    val restTemplate: TestRestTemplate
) {
    @Test
    fun loadingMainPage() {
        //when
        val body = this.restTemplate.getForObject("/", String::class.java)

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스")
    }
}