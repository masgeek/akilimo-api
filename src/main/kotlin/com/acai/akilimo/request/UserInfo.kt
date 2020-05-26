package com.acai.akilimo.request

import lombok.Data

@Data
class UserInfo(
        val deviceToken: String,
        var mobileCountryCode: String,
        var mobileNumber: String,
        var fullPhoneNumber: String,
        var userName: String,
        var fieldDescription: String
) {

    var emailAddress: String? = null

    var sendSms: Boolean = false
    var sendEmail: Boolean = false
}