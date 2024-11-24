package forloooop.speakly.application.service

import forloooop.speakly.adapter.web.data.response.TagApiResponseGroup
import forloooop.speakly.application.port.input.TagUseCase
import forloooop.speakly.application.port.output.TagOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class TagService(
    private val tagOutput: TagOutput
) : TagUseCase {

    override fun getAll(): List<TagApiResponseGroup.TagApiResponse> {
        val tags = tagOutput.getAll()
        return tags.map { TagApiResponseGroup.TagApiResponse(it) }
    }

    @Transactional
    override fun register(tagNames: List<String>): List<TagApiResponseGroup.TagApiResponse> {
        val tags = tagOutput.register(tagNames)
        return tags.map { TagApiResponseGroup.TagApiResponse(it) }
    }
}