package org.study.springbootweb.web

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.hamcrest.Matchers.`is`
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.security.test.context.support.WithMockUser
import org.study.springbootweb.config.auth.SecurityConfig

@ExtendWith(SpringExtension::class)
@WebMvcTest(
    controllers = [HelloController::class],
    excludeFilters = [
        ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = [SecurityConfig::class]
        )
    ]
)
class HelloControllerTest @Autowired constructor(
    private val mvc: MockMvc
) {
    @WithMockUser(roles = ["USER"])
    @Test
    fun helloReturn() {
        val hello = "hello"

        mvc.get("/hello")
            .andExpect { status { isOk() } }
            .andExpect { content { string(hello) } }
    }

    @WithMockUser(roles = ["USER"])
    @Test
    fun helloDtoReturn() {
        val name = "hello"
        val amount = 1000

        mvc.get("/hello/dto") {
            param("name", name)
            param("amount", amount.toString())
        }
            .andExpect { status { isOk() } }
            .andExpect { jsonPath("$.name", `is`(name)) }
            .andExpect { jsonPath("$.amount", `is`(amount)) }
    }
}