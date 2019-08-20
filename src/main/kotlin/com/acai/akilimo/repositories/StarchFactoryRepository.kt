package com.acai.akilimo.repositories

import com.acai.akilimo.entities.AvailableFertilizers
import com.acai.akilimo.entities.StarchFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StarchFactoryRepository : JpaRepository<StarchFactory, Long> {
fun findByFactoryActiveIsTrueAndCountryInOrderByFactoryNameDesc(countryCode: Collection<String>): List<StarchFactory>

fun findByFactoryActiveIsTrueAndCountryInOrderByFactoryNameAsc(countryCode: Collection<String>): List<StarchFactory>
}