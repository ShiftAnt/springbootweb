package org.study.springbootweb.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.study.springbootweb.web.dto.HelloResponseDto

@RestController
class HelloController {

    @GetMapping("/hello")
    fun hello() = "hello"

    @GetMapping("/hello/dto")
    fun helloDto(@RequestParam("name") name: String,
                 @RequestParam("amount") amount: Int): HelloResponseDto = HelloResponseDto(name, amount)
}