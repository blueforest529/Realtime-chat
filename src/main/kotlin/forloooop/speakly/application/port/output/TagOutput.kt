package forloooop.speakly.application.port.output

import forloooop.speakly.domain.entity.Tag

interface TagOutput {

    fun register(tagNames: List<String>): List<Tag>
    fun getAll(): List<Tag>

}