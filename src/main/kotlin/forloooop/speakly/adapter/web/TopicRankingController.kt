package forloooop.speakly.adapter.web

import forloooop.speakly.adapter.web.data.response.TopicResponseGroup
import forloooop.speakly.application.service.TopicRankingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/ranking")
class TopicRankingController(
    private val topicRankingService: TopicRankingService
) {

    @GetMapping("/topics")
    fun getTopicRanking(): List<TopicResponseGroup.TopicRankingResponse> {
        return topicRankingService.calculateTopicRanking()
    }
}
