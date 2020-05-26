package com.acai.akilimo.controllers

import com.acai.akilimo.request.PushNotificationRequest
import com.acai.akilimo.response.PushNotificationResponse
import com.acai.akilimo.service.PushNotificationService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v3/notifications")
@RestController
class PushNotificationsController(private val pushNotificationService: PushNotificationService) : BaseController() {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(PushNotificationsController::class.java)
    }

    @PostMapping("/notification/topic")
    fun sendNotification(@RequestBody request: PushNotificationRequest): ResponseEntity<*>? {
        pushNotificationService.sendPushNotificationWithoutData(request)
        return ResponseEntity<Any>(PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK)
    }

    @PostMapping("/notification/token")
    fun sendTokenNotification(@RequestBody request: PushNotificationRequest): ResponseEntity<*>? {
        pushNotificationService.sendPushNotificationToToken(request)
        return ResponseEntity<Any>(PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK)
    }

    @PostMapping("/notification/data")
    fun sendDataNotification(@RequestBody request: PushNotificationRequest): ResponseEntity<*>? {
        pushNotificationService.sendPushNotification(request)
        return ResponseEntity<Any>(PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK)
    }

    @GetMapping("/notification")
    fun sendSampleNotification(): ResponseEntity<*>? {
        pushNotificationService.sendSamplePushNotification()
        return ResponseEntity<Any>(PushNotificationResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK)
    }
}