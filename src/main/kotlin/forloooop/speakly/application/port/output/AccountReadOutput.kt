package forloooop.speakly.application.port.output

import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.enums.AuthType

interface AccountReadOutput {

    fun getAccountDetail(id: Long): Account
    fun existsByLinkedAuth(authType: AuthType, providerUserId: String): Boolean

    fun findByLinkedAuth(authType: AuthType, providerUserId: String): Account?
}
