package forloooop.speakly.application.port.output

import forloooop.speakly.domain.entity.Account

interface ProfileOutput {

    fun modifyProfile(account: Account): Account
}
