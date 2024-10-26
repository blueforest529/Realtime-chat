package forloooop.speakly.adapter.web.data.request

import org.springframework.data.elasticsearch.client.elc.NativeQuery
import org.springframework.data.elasticsearch.core.query.Query

class TopicApiRequestGroup {

    data class SearchApiRequest(
        val name: String
    ) {
        fun toSearchQuery(): Query {
            return NativeQuery.builder()
                .withQuery { q -> q.wildcard { m -> m.field("name").value("$name") } }
                .build()
        }
    }
}
