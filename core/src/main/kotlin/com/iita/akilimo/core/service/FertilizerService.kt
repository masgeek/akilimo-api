package com.iita.akilimo.core.service



import com.iita.akilimo.core.interfaces.IFertilizerService
import com.iita.akilimo.core.mapper.FertilizerDto
import com.iita.akilimo.core.request.FertilizerRequest
import com.iita.akilimo.database.entities.AvailableFertilizers
import com.iita.akilimo.database.repos.AvailableFertilizerRepo
import com.iita.akilimo.enums.EnumCountry
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FertilizerService

constructor(val availableFertilizerRepo: AvailableFertilizerRepo) : IFertilizerService {
    private val logger = LoggerFactory.getLogger(FertilizerService::class.java)

    private val modelMapper = ModelMapper()

    override fun fertilizers(countryCode: String, useCase: String?): List<FertilizerDto> {

        val countries = ArrayList<String>()
        countries.add(EnumCountry.ALL.name)
        countries.add(countryCode)


        val fertilizerList = if (useCase != null) {
            availableFertilizerRepo.findByAvailableIsTrueAndCountryInAndUseCaseOrderBySortOrderAscNameAsc(countries, useCase)
        } else {
            availableFertilizerRepo.findByAvailableIsTrueAndCountryInOrderBySortOrderAscNameAsc(countries)
        }

        val fertilizerPriceDtoList = ArrayList<FertilizerDto>()

        var currencyCode = EnumCountry.ALL.currency()
        val country = countryCode.toUpperCase()

        when (country) {
            EnumCountry.TZ.name -> {
                currencyCode = EnumCountry.TZ.currency()
            }
            EnumCountry.NG.name -> {
                currencyCode = EnumCountry.NG.currency()
            }
        }

        modelMapper.configuration.isSkipNullEnabled = true
        modelMapper.configuration.propertyCondition
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT

        for (fertilizer in fertilizerList) {
            val fertilizerDto = modelMapper.map(fertilizer, FertilizerDto::class.java)
            fertilizerDto.currency = currencyCode
            fertilizerDto.countryCode = country
            fertilizerDto.fertilizerCountry = "${fertilizer.type}-$country"

            fertilizerPriceDtoList.add(fertilizerDto)
        }

        return fertilizerPriceDtoList
    }

    override fun saveFertilizer(fertilizerRequest: FertilizerRequest): FertilizerDto? {
        val entity = modelMapper.map(fertilizerRequest, AvailableFertilizers::class.java)

        val saved = availableFertilizerRepo.save(entity)

        modelMapper.configuration.isSkipNullEnabled = true
        modelMapper.configuration.propertyCondition
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT

        return modelMapper.map(saved, FertilizerDto::class.java)
    }

    override fun updateFertilizer(id: Long, fertilizerRequest: FertilizerRequest): FertilizerDto? {
        val entity = availableFertilizerRepo.findById(id).get()

        modelMapper.configuration.isSkipNullEnabled = true
        modelMapper.configuration.propertyCondition
        modelMapper.configuration.matchingStrategy = MatchingStrategies.STRICT

        modelMapper.map(fertilizerRequest, entity)


        val saved = availableFertilizerRepo.save(entity)

        return modelMapper.map(saved, FertilizerDto::class.java)
    }

    @Transactional
    override fun deleteFertilizer(id: Long): Boolean {

        val entity = availableFertilizerRepo.findByFertilizerId(id)

        return when {
            entity != null -> {
                availableFertilizerRepo.deleteById(id)
                true
            }
            else -> false
        }

    }


}
