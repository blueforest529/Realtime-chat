package forloooop.speakly.adapter.persistance

import forloooop.speakly.adapter.persistance.jpa.TopicRepository
import forloooop.speakly.application.port.output.TopicOutput
import forloooop.speakly.domain.entity.Topic
import forloooop.speakly.domain.search.TopicSearch
import jakarta.transaction.Transactional
import org.springframework.data.elasticsearch.core.ElasticsearchOperations
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.core.query.Query
import org.springframework.stereotype.Service

@Service
class TopicAdaptor(
    private val topicRepository: TopicRepository,
    private val elasticsearchOperations: ElasticsearchOperations
) : TopicOutput {

    @Transactional
    override fun register(topic: Topic): Topic {
        elasticsearchOperations.save(topic.toSearch()) // 엘라스틱서치에 저장
        return topicRepository.save(topic) // 업데이트
    }

    override fun searchByQuery(query: Query): List<SearchHit<TopicSearch>> {
        val searchResult = elasticsearchOperations.search(query, TopicSearch::class.java)
        return searchResult.hasSearchHits().let { hasHits ->
            if (hasHits) {
                searchResult.searchHits
            } else {
                emptyList()
            }
        }
    }

    override fun findById(id: Long): Topic? {
        return topicRepository.findById(id).orElse(null)
    }
}
