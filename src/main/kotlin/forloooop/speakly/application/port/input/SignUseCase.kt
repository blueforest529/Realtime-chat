package forloooop.speakly.application.port.input

import forloooop.speakly.domain.interfaces.Entityable

interface SignUseCase <T> {

    fun signIn(entityable: Entityable<T>): T

    fun login()
}
