package com.iita.akilimo.core.interfaces


import com.iita.akilimo.core.mapper.PayloadDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface IPayloadCostService {

    fun findPayloadById(id: Long): PayloadDto?

    fun payloadList(pageable: Pageable): Page<PayloadDto>

    fun findPayloadByRequestId(requestId: String): List<PayloadDto>
}
