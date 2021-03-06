package com.iita.akilimo.core.mapper


import com.fasterxml.jackson.annotation.JsonProperty
import com.iita.akilimo.enums.EnumCountry

import java.time.LocalDateTime


class ProducePriceDto {

    var priceId: Long? = null

    var priceIndex: Long = 1

    var country: EnumCountry? = null

    var produceType: String? = null

    var minLocalPrice: Double = 0.0

    var maxLocalPrice: Double = 0.0

    var minAllowedPrice: Double = 0.0

    var maxAllowedPrice: Double = 0.0

    var minUsd: Double = 0.0

    var maxUsd: Double = 0.0

    var sortOrder: Int = 1

    var active: Boolean = false

    var createdAt: LocalDateTime? = null

    var updatedAt: LocalDateTime? = null

    @JsonProperty("averagePrice")
    fun averagePrice(): Double {
        return (minLocalPrice + maxLocalPrice) / 2
    }

    @JsonProperty("countryPrice")
    fun countryPrice(): String {
        return "$country$priceId";
    }
}
