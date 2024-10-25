package forloooop.speakly.application.port.output

import forloooop.speakly.adapter.web.data.response.TopicResponseGroup
import java.time.LocalDateTime

interface TopicRankingOutput {
    fun getTopicsWithRankingData(fromDate: LocalDateTime): List<TopicResponseGroup.TopicRankingResponse>
}
