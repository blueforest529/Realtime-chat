package forloooop.speakly.adapter.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class IndexController() {

    // 테스트용 index 추가
    @GetMapping("/")
    fun index(): String {
        println("test")
        return "index"
    }
}
