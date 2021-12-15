package org.study.springbootweb.web

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.study.springbootweb.config.auth.LoginUser
import org.study.springbootweb.config.auth.dto.SessionUser
import org.study.springbootweb.service.posts.PostsService

@Controller
class IndexController(
    val postsService: PostsService,
) {

    @GetMapping("/")
    fun index(
        model: Model,
        @LoginUser user: SessionUser?): String {
        model.addAttribute("posts", postsService.findAllDesc())

        if (user != null) {
            model.addAttribute("userName", user.name)
        }

        return "index"
    }

    @GetMapping("/posts/save")
    fun postsSave() = "posts-save"

    @GetMapping("/posts/update/{id}")
    fun postsUpdate(@PathVariable id: Long, model: Model): String {
        val dto = postsService.findById(id)
        model.addAttribute("post", dto)

        return "posts-update"
    }
}