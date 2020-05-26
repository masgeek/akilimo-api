package com.acai.akilimo.service

import com.acai.akilimo.config.AppProperties
import com.acai.akilimo.interfaces.IMessagingService
import com.acai.akilimo.mapper.RecommendationResponseDto
import com.acai.akilimo.properties.MessagingProperties
import com.acai.akilimo.request.SmsMessage
import com.acai.akilimo.response.MessageSendingResponse
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.base.Strings
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class MessagingService
@Autowired

constructor(appProperties: AppProperties) : IMessagingService {

    private val logger = LoggerFactory.getLogger(MessagingService::class.java)
    private val sms: MessagingProperties = appProperties.sms()

    private val phoneUtil = PhoneNumberUtil.getInstance()

    private val mapper = ObjectMapper()
    private val restTemplate = RestTemplate()

    @Suppress("NAME_SHADOWING")
    override fun sendTextMessage(response: RecommendationResponseDto, sendSms: Boolean) {
        if (!sendSms) {
            return
        }
        val smsMessage = buildMessagePayload(response)


        val postUrl = sms.apiUrl()
        val headers = addRequestHeaders()
        val entity = HttpEntity(smsMessage, headers)

        try {
            val response = restTemplate.postForEntity(postUrl, entity, MessageSendingResponse::class.java)
            val responseString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response)
            logger.info(responseString)
        } catch (ex: Exception) {
            logger.error(ex.message, ex)
        }
    }

    private fun buildMessagePayload(response: RecommendationResponseDto): SmsMessage {

        val message = SmsMessage(userName = sms.smsUser, password = sms.smsToken)
        message.mobileNumber = response.mobileNumber

        when {
            !Strings.isNullOrEmpty(response.fertilizerRecText) &&
                    response.fertilizerRecText != "We donot have fertilizer recommendation for your location." -> {
                message.smsText = response.fertilizerRecText

            }
        }

        when {
            !Strings.isNullOrEmpty(response.interCroppingRecText) -> {
                message.smsText = response.interCroppingRecText
            }
        }

        when {
            !Strings.isNullOrEmpty(response.plantingPracticeRecText) -> {
                message.smsText = response.plantingPracticeRecText
            }
        }

        when {
            !Strings.isNullOrEmpty(response.scheduledPlantingRecText) -> {
                message.smsText = response.scheduledPlantingRecText
            }
        }
        return message
    }

    override fun sendEmailMessage(response: RecommendationResponseDto, email: Boolean) {
        if (!email) {
            return
        }
        logger.info("This feature for sending emails has not been implemented yet, please try again later ${response.userEmail}")
    }

    fun processPhoneNumber(recommendationResponseDto: RecommendationResponseDto): Phonenumber.PhoneNumber? {
        val countryCode = recommendationResponseDto.country
        val phoneNumber = recommendationResponseDto.mobileNumber?.toLong()

        return phoneUtil.parse(phoneNumber.toString(), countryCode)
    }

    fun convertToInternationalNumber(recommendationResponseDto: RecommendationResponseDto): String? {

        val phoneNumber = processPhoneNumber(recommendationResponseDto)

        return phoneUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164)
    }

    private fun addRequestHeaders(): HttpHeaders {
        val headers = HttpHeaders()

        headers.contentType = MediaType.APPLICATION_JSON

        return headers
    }
}