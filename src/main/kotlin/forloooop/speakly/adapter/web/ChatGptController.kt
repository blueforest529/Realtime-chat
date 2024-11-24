package forloooop.speakly.adapter.web

import forloooop.speakly.application.port.input.ChatUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class ChatGptController(private val chatUseCase: ChatUseCase) {

    @GetMapping("/chatgpt")
    fun chatgpt(@RequestParam prompt: String): String {
        val request = ChatRequest(prompt)
        return chatUseCase.chat(request)
    }
}