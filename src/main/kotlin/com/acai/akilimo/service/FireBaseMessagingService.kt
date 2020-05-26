package com.acai.akilimo.service

import com.acai.akilimo.config.AppProperties
import com.acai.akilimo.properties.FireBaseProperties
import com.acai.akilimo.properties.MessagingProperties
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.Message
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.FileInputStream
import java.io.IOException
import java.util.concurrent.ExecutionException
import javax.annotation.PostConstruct


@Service
class FireBaseMessagingService(appProperties: AppProperties) {

    private val logger = LoggerFactory.getLogger(FireBaseMessagingService::class.java)


    private val firebase: FireBaseProperties = appProperties.firebase()


    @PostConstruct
    fun initializeWithCredentials() {
        try {
            val refreshToken = FileInputStream(firebase.configFile)
            val options = FirebaseOptions.Builder()
//                    .setCredentials(GoogleCredentials.getApplicationDefault())
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


    @Throws(InterruptedException::class, ExecutionException::class)
    fun sendBatchMessages(): String? {
        val registrationToken = "cTURRvGlR4e3SQYzwjzOEa:APA91bFMZ1hLAhtiAsDyzTsYc-X8Kln_EJcy7eS6cV-nT2aPudIz4NpEL6LTN6efWDxspZt5kJ0tFLOUgXpBmtgjsrNte7P8p-xP11fpfUEJGJdrJ77ki03dfpFa9-2kLOtHN2jMzFT1"
        val message: Message = Message.builder()
                .putData("score", "850")
                .putData("time", "2:45")
                .setToken(registrationToken)
                .build()

        val response = FirebaseMessaging.getInstance().send(message)

        return response
    }
}