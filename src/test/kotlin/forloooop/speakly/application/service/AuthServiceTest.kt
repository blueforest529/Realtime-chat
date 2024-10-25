package forloooop.speakly.application.service

import forloooop.speakly.adapter.persistance.jpa.AccountRepository
import forloooop.speakly.application.port.input.AuthReadCase
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.LinkedAuth
import forloooop.speakly.domain.enums.AuthType
import jakarta.transaction.Transactional
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@DisplayName("AuthService 통합 테스트")
class AuthServiceTest {

    @Autowired
    lateinit var authReadCase: AuthReadCase

    @Autowired
    lateinit var accountRepository: AccountRepository

    @BeforeEach
    @Transactional
    fun setUp() {
        val linkedAuth = LinkedAuth(AuthType.KAKAO, "providerUserId")
        val account = Account("email", "name")
        account.addLinkedAuth(linkedAuth)

        accountRepository.save(account)
    }

    @ParameterizedTest
    @MethodSource("provideAuthValueGroup")
    fun hasDuplicated(authType: AuthType, providerUserId: String) {
        // Given & When
        val result = authReadCase.hasDuplicated(authType, providerUserId)

        // Then
        assertThat(result).isTrue()
    }

    companion object {
        @JvmStatic
        fun provideAuthValueGroup() = listOf(
            Arguments.of(AuthType.KAKAO, "providerUserId"),
        )
    }
}
