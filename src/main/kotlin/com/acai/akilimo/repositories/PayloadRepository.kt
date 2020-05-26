package com.acai.akilimo.repositories

import com.acai.akilimo.entities.Payload
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PayloadRepository : JpaRepository<Payload, Long> {

    fun findByIdOrderByCreatedAtAsc(id: Long): Payload?

    override fun findAll(): List<Payload>

    fun findAllByDeviceToken(deviceToken: String): List<Payload>
}
