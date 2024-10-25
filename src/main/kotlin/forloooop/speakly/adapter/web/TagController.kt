package forloooop.speakly.adapter.web

import forloooop.speakly.adapter.web.data.response.TagApiResponseGroup
import forloooop.speakly.application.port.input.TagUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/tag")
class TagController(
    private var tagUseCase: TagUseCase
) {

    @GetMapping
    fun getAll(): ResponseEntity<List<TagApiResponseGroup.TagApiResponse>> {
        val tags = tagUseCase.getAll()
        return ResponseEntity.ok(tags)
    }

    @PostMapping("/register")
    fun registerTag(@RequestBody tagNames: List<String>): ResponseEntity<List<TagApiResponseGroup.TagApiResponse>> {
        val tags = tagUseCase.register(tagNames)
        return ResponseEntity.ok(tags)
    }

}
