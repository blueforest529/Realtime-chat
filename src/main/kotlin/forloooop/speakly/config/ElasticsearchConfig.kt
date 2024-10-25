package io.message.message.config

import org.apache.http.conn.ssl.TrustAllStrategy
import org.apache.http.ssl.SSLContextBuilder
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchProperties
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration
import java.security.KeyManagementException
import java.security.KeyStoreException
import java.security.NoSuchAlgorithmException
import java.time.Duration
import javax.net.ssl.SSLContext

@Configuration
class ElasticsearchConfig : ElasticsearchConfiguration() {
    private val elasticsearchProperties: ElasticsearchProperties? = null

    override fun clientConfiguration(): ClientConfiguration {
        return ClientConfiguration.builder()
            .connectedTo(*elasticsearchProperties!!.uris.toTypedArray<String>())
            .usingSsl(disableSslVerification()!!)
            .withBasicAuth(
                elasticsearchProperties.username,
                elasticsearchProperties.password
            )
            .withConnectTimeout(Duration.ofSeconds(5))
            .withSocketTimeout(Duration.ofSeconds(3))
            .build()
    }

    companion object {
        fun disableSslVerification(): SSLContext? {
            try {
                return SSLContextBuilder.create()
                    .setProtocol("SSL")
                    .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                    .build()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: KeyStoreException) {
                e.printStackTrace()
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            }
            return null
        }
    }
}
