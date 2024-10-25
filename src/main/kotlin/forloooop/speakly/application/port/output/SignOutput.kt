package forloooop.speakly.application.port.output

import forloooop.speakly.domain.interfaces.Entityable

interface SignOutput <T> {

    fun saveAccount(entityable: Entityable<T>): T

    fun login()
}
