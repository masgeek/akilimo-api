package com.iita.akilimo.core.service


import com.iita.akilimo.core.mapper.RequestStatsDto
import com.iita.akilimo.database.repos.RequestStatsViewRepo
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class RequestStatsService
constructor(
    val statsViewRepo: RequestStatsViewRepo
) {
    private val logger = LoggerFactory.getLogger(RequestStatsService::class.java)

    private val modelMapper = ModelMapper()

    fun getRequestStats(pageable: Pageable): Page<RequestStatsDto> {
        val statsList = statsViewRepo.findAllByOrderByIdDesc(pageable)

        return statsList.map { statsViewEntity ->
            modelMapper.map(statsViewEntity, RequestStatsDto::class.java)
        }
    }


}