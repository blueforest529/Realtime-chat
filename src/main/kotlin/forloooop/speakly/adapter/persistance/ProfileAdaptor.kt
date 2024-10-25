package forloooop.speakly.adapter.persistance

import forloooop.speakly.adapter.persistance.jpa.AccountRepository
import forloooop.speakly.application.port.output.ProfileOutput
import forloooop.speakly.domain.entity.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProfileAdaptor(
    private val accountRepository: AccountRepository
):ProfileOutput {
    override fun modifyProfile(account: Account): Account {
        return accountRepository.save(account)
    }
}
