package io.message.message.domain.search.base

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

abstract class Search {

    @Id
    @Field(name = "id", type = FieldType.Text)
    protected var id: String? = null
}
