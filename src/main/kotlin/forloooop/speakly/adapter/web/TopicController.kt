package forloooop.speakly.adapter.web

import forloooop.speakly.adapter.web.data.request.TopicApiRequestGroup
import forloooop.speakly.application.port.output.TopicOutput
import forloooop.speakly.application.service.AccountService
import forloooop.speakly.application.service.TopicService
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.Topic
import forloooop.speakly.domain.search.TopicSearch
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/topic")
@Tag(name = "Topic API", description = "Topic과 관련된 작업을 수행합니다. (검색, 생성, 나가기 등)")
class TopicController(
    private val topicService: TopicService,
    private val topicOutput: TopicOutput,
    private val accountService: AccountService
) {
    @PostMapping
    fun saveTopic(@RequestBody topic: Topic): ResponseEntity<Topic> {
        val savedTopic = topicService.saveTopic(topic)
        return ResponseEntity.ok(savedTopic)
    }

    @GetMapping
    fun searchTopic(@RequestBody request: TopicApiRequestGroup.SearchApiRequest): ResponseEntity<List<SearchHit<TopicSearch>>> {
        val query = request.toSearchQuery()

        return ResponseEntity.ok(topicOutput.searchByQuery(query))
    }

    @PostMapping("/{topicId}/participate")
    fun participateInTopic(
        @Parameter(description = "Topic Id")
        @PathVariable topicId: Long,
        @Parameter(description = "Account Id")
        @RequestParam accountId: Long
    ) {
        val account: Account = accountService.getAccountDetail(accountId)
        val topic: Topic = topicService.findTopicById(topicId)

        topicService.participateInTopic(account, topic)
    }

    @DeleteMapping("/{topicId}/leave")
    fun leaveTopic(
        @PathVariable topicId: Long,
        @RequestParam accountId: Long
    ) {
        val account: Account = accountService.getAccountDetail(accountId)
        val topic: Topic = topicService.findTopicById(topicId)

        topicService.leaveTopic(account, topic)
    }
}
