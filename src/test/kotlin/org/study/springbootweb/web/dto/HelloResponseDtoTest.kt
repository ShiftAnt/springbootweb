package org.study.springbootweb.web.dto

import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat

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