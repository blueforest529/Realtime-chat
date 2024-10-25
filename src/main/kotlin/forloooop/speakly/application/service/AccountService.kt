package forloooop.speakly.application.service

import forloooop.speakly.application.port.input.AccountReadCase
import forloooop.speakly.application.port.input.ProfileUseCase
import forloooop.speakly.application.port.input.SignUseCase
import forloooop.speakly.application.port.output.AccountReadOutput
import forloooop.speakly.application.port.output.ProfileOutput
import forloooop.speakly.application.port.output.SignOutput
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.interfaces.Entityable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountService(
    private val signOutput: SignOutput<Account>,
    private val profileOutput: ProfileOutput,
    private val accountReadOutput: AccountReadOutput
) : SignUseCase<Account>, ProfileUseCase, AccountReadCase {

    @Transactional
    override fun signIn(entityable: Entityable<Account>): Account {
        return signOutput.saveAccount(entityable)
    }

    override fun login() {
        TODO("Not yet implemented")
    }

    override fun getAccountDetail(id: Long): Account {
        return accountReadOutput.getAccountDetail(id)
    }

    @Transactional
    override fun modifyProfile(id: Long, nickname: String): Account {
        val account = getAccountDetail(id)
        account.modifyProfile(nickname)
        return profileOutput.modifyProfile(account)
    }
}
