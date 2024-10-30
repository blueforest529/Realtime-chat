package forloooop.speakly.config.websocket

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import forloooop.speakly.adapter.persistance.jpa.TopicRepository
import forloooop.speakly.domain.entity.Topic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class TopicDeserializer : JsonDeserializer<Topic>() {

    @Autowired
    private lateinit var topicRepository: TopicRepository

    override fun deserialize(parser: JsonParser, context: DeserializationContext): Topic {
        val topicId = parser.longValue
        return topicRepository.findById(topicId)
            .orElseThrow { IllegalArgumentException("Invalid topic ID: $topicId") }
    }
}