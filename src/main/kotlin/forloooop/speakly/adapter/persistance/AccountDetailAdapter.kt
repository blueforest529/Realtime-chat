package forloooop.speakly.adapter.persistance

import com.linecorp.kotlinjdsl.dsl.jpql.jpql
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderContext
import com.linecorp.kotlinjdsl.render.jpql.JpqlRenderer
import forloooop.speakly.adapter.persistance.jpa.AccountRepository
import forloooop.speakly.application.port.output.AccountReadOutput
import forloooop.speakly.domain.entity.Account
import forloooop.speakly.domain.entity.LinkedAuth
import forloooop.speakly.domain.enums.AuthType
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Service

@Service
class AccountDetailAdapter(
    private var accountRepository: AccountRepository,

    @PersistenceContext
    private var entityManager: EntityManager,

    private var jpqlRenderer: JpqlRenderContext
) : AccountReadOutput {


    override fun getAccountDetail(id: Long): Account {
        val query = jpql {
            select(
                entity(Account::class)
            ).from(
                entity(Account::class)
            ).where(
                path(Account::id).eq(id)
            )
        }

        val rendered = JpqlRenderer().render(query, jpqlRenderer)
        val result = entityManager.createQuery(rendered.query, Account::class.java)
            .apply {
                rendered.params.forEach(::setParameter)
                setMaxResults(1)
            }.resultList

        return result.firstOrNull() ?: throw NoSuchElementException("Account not found with id: $id")
    }

    override fun existsByLinkedAuth(authType: AuthType, providerUserId: String): Boolean {
        val query = jpql {
            select(
                path(LinkedAuth::id)
            ).from(
                entity(LinkedAuth::class)
            ).where(
                and(
                    path(LinkedAuth::type).eq(authType),
                    path(LinkedAuth::providerUserId).eq(providerUserId)
                )
            )
        }

        val rendered = JpqlRenderer().render(query, jpqlRenderer)
        return entityManager.createQuery(rendered.query)
            .apply {
                rendered.params.forEach(::setParameter)
                setMaxResults(1)
            }.resultList.isNotEmpty()
    }

    override fun findByLinkedAuth(authType: AuthType, providerUserId: String): Account? {
        val query = jpql {
            select(
                entity(Account::class)
            ).from(
                entity(LinkedAuth::class),
                join(LinkedAuth::account)
            ).where(
                and(
                    path(LinkedAuth::type).eq(authType),
                    path(LinkedAuth::providerUserId).eq(providerUserId)
                )
            )
        }

        val rendered = JpqlRenderer().render(query, jpqlRenderer)
        val result = entityManager.createQuery(rendered.query, Account::class.java)
            .apply {
                rendered.params.forEach(::setParameter)
                setMaxResults(1)
            }.resultList

        return result.firstOrNull()
    }
}
