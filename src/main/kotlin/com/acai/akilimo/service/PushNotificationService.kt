package com.acai.akilimo.service

import com.acai.akilimo.config.AppProperties
import com.acai.akilimo.firebase.FCMService
import com.acai.akilimo.properties.FireBaseProperties
import com.acai.akilimo.request.PushNotificationRequest
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ExecutionException

@Service
class PushNotificationService(private val fcmService: FCMService,
                              appProperties: AppProperties) {

    private val logger = LoggerFactory.getLogger(PushNotificationService::class.java)


    private val firebase: FireBaseProperties = appProperties.firebase()

    private val defaults: Map<String, String>


    init {
        this.defaults = firebase.defaults!!
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    fun sendSamplePushNotification() {
        try {
            fcmService.sendMessageWithoutData(samplePushNotificationRequest())
        } catch (e: InterruptedException) {
            logger.error(e.message)
        } catch (e: ExecutionException) {
            logger.error(e.message)
        }
    }

    fun sendPushNotification(request: PushNotificationRequest) {
        try {
            val payloadData = samplePayloadData()
            fcmService.sendMessage(payloadData, request)
        } catch (e: InterruptedException) {
            logger.error(e.message)
        } catch (e: ExecutionException) {
            logger.error(e.message)
        }
    }

    fun sendPushNotificationWithoutData(request: PushNotificationRequest) {
        try {
            fcmService.sendMessageWithoutData(request)
        } catch (e: InterruptedException) {
            logger.error(e.message)
        } catch (e: ExecutionException) {
            logger.error(e.message)
        }
    }

    fun sendPushNotificationToToken(request: PushNotificationRequest) {
        try {
            fcmService.sendMessageToToken(request)
        } catch (e: InterruptedException) {
            logger.error(e.message)
        } catch (e: ExecutionException) {
            logger.error(e.message)
        }
    }

    fun samplePayloadData(): MutableMap<String, String?> {
        val pushData: MutableMap<String, String?> = HashMap()
        pushData["messageId"] = defaults["payloadMessageId"]
        pushData["text"] = defaults["payloadData"].toString() + " " + LocalDateTime.now()

        return pushData
    }

    fun samplePushNotificationRequest(): PushNotificationRequest {
        val title: String? = defaults.get("title")
        val message: String? = defaults.get("message")
        val topic: String? = defaults.get("topic")
        return PushNotificationRequest(
                title,
                message,
                topic)
    }
}