package forloooop.speakly.adapter.web.data.response

import forloooop.speakly.domain.entity.Topic

class TopicResponseGroup {

    data class TopicRankingResponse(
        val topic: Topic,
        val score: Double,
        val numberOfAttendee: Int,
        val recentMessageCount: Long
    )
}
