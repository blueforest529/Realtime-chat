package forloooop.speakly.application.port.input

import forloooop.speakly.domain.entity.Account

interface ProfileUseCase {

    fun modifyProfile(id: Long, nickname: String): Account
}
