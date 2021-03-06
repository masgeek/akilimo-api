package com.iita.akilimo.database.repos

import com.iita.akilimo.database.entities.StarchFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StarchFactoryRepository : JpaRepository<StarchFactory, Long> {
fun findByFactoryActiveIsTrueAndCountryInOrderByFactoryNameDesc(countryCode: Collection<String>): List<StarchFactory>

fun findByFactoryActiveIsTrueAndCountryInOrderByFactoryNameAsc(countryCode: Collection<String>): List<StarchFactory>

fun findByFactoryActiveIsTrueAndCountryInOrderBySortOrderAscFactoryNameAsc(countryCode: Collection<String>): List<StarchFactory>
}
