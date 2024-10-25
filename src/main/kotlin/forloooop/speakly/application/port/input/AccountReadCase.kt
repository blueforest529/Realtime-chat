package forloooop.speakly.application.port.input

import forloooop.speakly.domain.entity.Account

interface AccountReadCase {

    fun getAccountDetail(id: Long): Account
}
