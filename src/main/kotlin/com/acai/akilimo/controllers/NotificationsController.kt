package com.acai.akilimo.controllers

import com.acai.akilimo.service.FireBaseMessagingService
import com.google.firebase.FirebaseApp
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/v2/notifications")
@RestController
class NotificationsController(private val fireBaseMessagingService: FireBaseMessagingService) : BaseController() {
    companion object {
        val logger: Logger = LoggerFactory.getLogger(NotificationsController::class.java)
    }

    @PostMapping
    fun fireBase(): String? {
        return fireBaseMessagingService.sendBatchMessages()
    }
}