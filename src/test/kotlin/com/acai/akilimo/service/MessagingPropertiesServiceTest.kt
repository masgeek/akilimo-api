package com.acai.akilimo.service

import com.acai.akilimo.config.AkilimoConfigProperties
import com.acai.akilimo.mapper.RecommendationResponseDto
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import retrofit.RetrofitError
import java.util.NoSuchElementException


internal class MessagingPropertiesServiceTest {

    private val recommendationResponseDto = RecommendationResponseDto()

    private val service = MessagingService(akilimoConfigProperties = AkilimoConfigProperties())

    /*@Ignore
    @Test()
    fun sms_response_should_not_be_null() {
        recommendationResponseDto.country = "KE"
        recommendationResponseDto.mobileNumber = "0713196504"
        recommendationResponseDto.fertilizerRecText = "Fertilizer recommendation text"
        val smsResponse = service.sendTextMessage(recommendationResponseDto)


        Assertions.assertThrows(
                RetrofitError::class.java
        ) {
            smsResponse
        }
    }*/

    @Test
    fun testNumberFormatting() {
        recommendationResponseDto.country = "KE"
        recommendationResponseDto.mobileCountryCode = "254"
        recommendationResponseDto.mobileNumber = "0713196504"
        val phone = service.processPhoneNumber(recommendationResponseDto)

        Assert.assertEquals("Country Code: 254 National Number: 713196504", phone.toString())
    }

    @Test
    fun should_return_international_number() {
        recommendationResponseDto.country = "KE"
        recommendationResponseDto.mobileNumber = "0713196504"
        val phone = service.convertToInternationalNumber(recommendationResponseDto)

        Assert.assertEquals("+254713196504", phone)
    }
}