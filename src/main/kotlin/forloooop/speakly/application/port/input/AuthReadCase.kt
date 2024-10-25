package forloooop.speakly.application.port.input

import forloooop.speakly.domain.enums.AuthType

interface AuthReadCase {

    fun hasDuplicated(authType: AuthType, providerUserId: String): Boolean
}
