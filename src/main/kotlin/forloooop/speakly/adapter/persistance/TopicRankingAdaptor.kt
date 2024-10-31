package forloooop.speakly.adapter.persistance

import forloooop.speakly.adapter.persistance.jpa.ChatMessageRepository
import forloooop.speakly.adapter.persistance.jpa.TopicRepository
import forloooop.speakly.adapter.web.data.response.TopicResponseGroup
import forloooop.speakly.application.port.output.TopicRankingOutput
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicRankingAdaptor(
    private val topicRepository: TopicRepository,
    private val chatMessageRepository: ChatMessageRepository
) : TopicRankingOutput {
    override fun getTopicsWithRankingData(fromDate: LocalDateTime): List<TopicResponseGroup.TopicRankingResponse> {
        val topics = topicRepository.findAllByOrderByNumberOfAttendeeDesc()

        return topics.map { topic ->
            val recentMessageCount = chatMessageRepository.countByTopicAndCreatedAtAfter(topic, fromDate)
            TopicResponseGroup.TopicRankingResponse(
                topic = topic,
                score = 0.0,
                numberOfAttendee = topic.numberOfAttendee,
                recentMessageCount = recentMessageCount
            )
        }
    }
}
