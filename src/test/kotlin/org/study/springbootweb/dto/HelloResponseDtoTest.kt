package org.study.springbootweb.dto

import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat
import org.study.springbootweb.web.dto.HelloResponseDto

class HelloResponseDtoTest {

    @Test
    fun lombokFunctionTest() {
        val name = "test"
        val amount = 1000

        val dto = HelloResponseDto(name, amount)
        assertThat(dto.name).isEqualTo(name)
        assertThat(dto.amount).isEqualTo(amount)
    }
}