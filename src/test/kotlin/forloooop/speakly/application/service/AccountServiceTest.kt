package forloooop.speakly.application.service

import forloooop.speakly.adapter.persistance.jpa.AccountRepository
import forloooop.speakly.application.port.input.AccountReadCase
import forloooop.speakly.application.port.input.ProfileUseCase
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.LinkedAuth
import forloooop.speakly.domain.enums.AuthType
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("AccountService 통합 테스트")
class AccountServiceTest {

    @Autowired
    lateinit var profileUseCase: ProfileUseCase

    @Autowired
    lateinit var accountReadCase: AccountReadCase

    @Autowired
    lateinit var accountRepository: AccountRepository

    lateinit var testAccount: Account

    @BeforeEach
    @Transactional
    fun setUp() {
        val account = Account("email", "nickname")
        testAccount = accountRepository.save(account)
    }

    @Test
    fun signIn() {
    }

    @Test
    fun login() {
    }

    @Test
    @DisplayName("단일 계정 조회 테스트")
    fun getAccountDetail() {
        // Given
        val id = testAccount.id

        // When
        val result = accountReadCase.getAccountDetail(id!!)

        // Then
        assertNotNull(result)
        assertEquals(testAccount.id, result.id)
        assertEquals(testAccount.email, result.email)
        assertEquals(testAccount.nickname, result.nickname)
    }

    @Test
    @DisplayName("프로필 수정 테스트")
    fun modifyProfile() {
        // Given
        val id = testAccount.id
        val newNickname = "newNickname"

        // When
        val modifiedAccount = profileUseCase.modifyProfile(id!!, newNickname)

        // Then
        assertNotNull(modifiedAccount)
        assertEquals(id, modifiedAccount.id)
        assertEquals(newNickname, modifiedAccount.nickname)

        // Verify the change is persisted
        val persistedAccount = accountRepository.findById(id).orElseThrow()
        assertEquals(newNickname, persistedAccount.nickname)
    }
}
