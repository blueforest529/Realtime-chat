package forloooop.speakly.adapter.web.data.request

import org.springframework.data.elasticsearch.core.query.Criteria
import org.springframework.data.elasticsearch.core.query.CriteriaQuery

class TopicApiRequestGroup {

    data class SearchApiRequest(
        val name: String
    ) {
        fun toSearchQuery() = CriteriaQuery(Criteria.where("name").`is`(name))
    }
}
