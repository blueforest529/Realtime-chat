package forloooop.speakly.application.port.output

import forloooop.speakly.domain.entity.Topic
import forloooop.speakly.domain.search.TopicSearch
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.core.query.Query

interface TopicOutput {

    fun register(topic: Topic): Topic

    fun searchByQuery(query: Query): List<SearchHit<TopicSearch>>

    fun findById(id: Long): Topic?
}
