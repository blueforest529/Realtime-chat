package forloooop.speakly.adapter.persistance

import forloooop.speakly.adapter.persistance.jpa.AccountRepository
import forloooop.speakly.application.port.output.SignOutput
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.interfaces.Entityable
import org.springframework.stereotype.Service

@Service
class SignAdapter(
    private val accountRepository: AccountRepository
) : SignOutput <Account> {

    override fun saveAccount(entityable: Entityable<Account>): Account {
        return accountRepository.save(entityable.toEntity())
    }

    override fun login() {
        TODO("Not yet implemented")
    }
}
