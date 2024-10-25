package forloooop.speakly.application.port.input

import forloooop.speakly.adapter.web.data.response.TagApiResponseGroup

interface TagUseCase {

    fun register(tagNames: List<String>): List<TagApiResponseGroup.TagApiResponse>
    fun getAll(): List<TagApiResponseGroup.TagApiResponse>

}