package com.acai.akilimo.config

import com.acai.akilimo.properties.CurrencyProperties
import com.acai.akilimo.properties.FireBaseProperties
import com.acai.akilimo.properties.MessagingProperties
import com.acai.akilimo.properties.PlumberProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppProperties {

    @Bean
    @ConfigurationProperties(prefix = "akilimo.currency")
    fun currency(): CurrencyProperties {
        return CurrencyProperties()
    }


    @Bean
    @ConfigurationProperties(prefix = "messaging")
    fun globalProperties(): MessagingProperties {
        return MessagingProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "plumber")
    fun plumber(): PlumberProperties {
        return PlumberProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "messaging.sms")
    fun sms(): MessagingProperties {
        return MessagingProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "messaging.email")
    fun email(): MessagingProperties {
        return MessagingProperties()
    }

    @Bean
    @ConfigurationProperties(prefix = "messaging.firebase")
    fun firebase(): FireBaseProperties {
        return FireBaseProperties()
    }
}