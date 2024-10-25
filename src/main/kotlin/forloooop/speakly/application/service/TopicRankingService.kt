package forloooop.speakly.application.service

import forloooop.speakly.adapter.web.data.response.TopicResponseGroup
import forloooop.speakly.application.port.output.TopicRankingOutput
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicRankingService(
    private val topicRankingOutput: TopicRankingOutput
) {

    fun calculateTopicRanking(): List<TopicResponseGroup.TopicRankingResponse> {
        val now = LocalDateTime.now()
        val oneWeekAgo = now.minusDays(7)

        val topicsWithRankingData = topicRankingOutput.getTopicsWithRankingData(oneWeekAgo)

        return topicsWithRankingData.map { rankingData ->
            val score = calculateScore(rankingData)
            TopicResponseGroup.TopicRankingResponse(
                topic = rankingData.topic,
                score = score,
                numberOfAttendee = rankingData.numberOfAttendee,
                recentMessageCount = rankingData.recentMessageCount
            )
        }.sortedByDescending { it.score }
    }

    private fun calculateScore(rankingData: TopicResponseGroup.TopicRankingResponse): Double {
        val baseScore = rankingData.numberOfAttendee * 0.3
        val recentActivityScore = rankingData.recentMessageCount * 0.4
        return baseScore + recentActivityScore
    }
}
