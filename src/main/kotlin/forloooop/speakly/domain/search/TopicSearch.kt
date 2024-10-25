package forloooop.speakly.domain.search

import io.message.message.domain.search.base.Search
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

@Document(indexName = "topic")
class TopicSearch : Search() {

    @Field(name = "name", type = FieldType.Text)
    var name: String = ""

    @Field(name = "description", type = FieldType.Text)
    var description: String? = null

    @Field(name = "numberOfAttendee", type = FieldType.Integer)
    var numberOfAttendee: Int = 0

    @Field(name = "isPublic", type = FieldType.Boolean)
    var isPublic: Boolean = true
}
