package forloooop.speakly.adapter.web.data.response

import forloooop.speakly.domain.entity.Tag

class TagApiResponseGroup {

    data class TagApiResponse(
        val id: Long,
        val name: String,
    ) {
        constructor(tag: Tag) : this(
            id = tag.id!!,
            name = tag.name,
        )
    }

}