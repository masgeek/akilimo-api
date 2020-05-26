package com.acai.akilimo.firebase

import com.acai.akilimo.config.AppProperties
import com.acai.akilimo.properties.FireBaseProperties
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.IOException
import javax.annotation.PostConstruct

@Service
class FCMInitializer(appProperties: AppProperties) {
    private val firebase: FireBaseProperties = appProperties.firebase()

    var logger: Logger = LoggerFactory.getLogger(FCMInitializer::class.java)

    @PostConstruct
    fun initialize() {
        try {
            val refreshToken = FileInputStream(firebase.configFile!!)
            val options = FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(refreshToken))
                    .build()
            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options)
                logger.info("Firebase application has been initialized")
            }
        } catch (e: IOException) {
            logger.error(e.message)
        }
    }
}