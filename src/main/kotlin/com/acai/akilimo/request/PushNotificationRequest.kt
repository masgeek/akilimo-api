package com.acai.akilimo.request

class PushNotificationRequest(
        var title: String? = null,
        var message: String? = null,
        var topic: String? = null,
        var token: String? = null
)