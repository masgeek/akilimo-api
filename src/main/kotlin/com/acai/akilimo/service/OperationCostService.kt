package com.acai.akilimo.service


import com.acai.akilimo.config.AppProperties
import com.acai.akilimo.entities.OperationCost
import com.acai.akilimo.interfaces.IOperationCostService
import com.acai.akilimo.mapper.OperationCostDto
import com.acai.akilimo.repositories.OperationCostRepository
import com.acai.akilimo.request.OperationCostRequest
import com.acai.akilimo.utils.CurrencyConversion
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class OperationCostService
@Autowired
constructor(
        private val operationCostRepository: OperationCostRepository,
        appProperties: AppProperties
) : IOperationCostService {
    private val logger = LoggerFactory.getLogger(OperationCostService::class.java)
    private val currencyProperties = appProperties.currency()

    val conversion: CurrencyConversion = CurrencyConversion()

    private val modelMapper = ModelMapper()

    override fun operationCost(id: Long): OperationCostDto? {
        val operationCost = operationCostRepository.findById(id).get()

        val operationCostDto = modelMapper.map(operationCost, OperationCostDto::class.java)

        operationCostDto.averageNgnPrice = operationCostDto.getAverageNgn()
        operationCostDto.averageTzsPrice = operationCostDto.getAverageTzs()
        operationCostDto.averageUsdPrice = operationCostDto.getAverageUsd()

        return operationCostDto
    }

    override fun operationCostList(opName: String, opType: String): List<OperationCostDto> {
        val operationCostList = operationCostRepository.findByActiveIsTrueAndOperationNameAndOperationTypeOrderByMaxUsdAsc(opName, opType)
        val priceDtoList = ArrayList<OperationCostDto>()

        var index = 0
        for (operationCost in operationCostList) {
            val operationCostDto = modelMapper.map(operationCost, OperationCostDto::class.java)

            operationCostDto.listIndex = index
            operationCostDto.averageNgnPrice = operationCostDto.getAverageNgn()
            operationCostDto.averageTzsPrice = operationCostDto.getAverageTzs()
            operationCostDto.averageUsdPrice = operationCostDto.getAverageUsd()

            priceDtoList.add(operationCostDto);
            index++
        }

        //add another for manual pricing
        val exactPrice: OperationCostDto = OperationCostDto()
        exactPrice.id = 10000
        exactPrice.listIndex = index
        exactPrice.operationName = opName
        exactPrice.operationType = opType
        exactPrice.averageNgnPrice = -1.0
        exactPrice.averageTzsPrice = -1.0
        exactPrice.averageUsdPrice = -1.0

        priceDtoList.add(exactPrice)
        return priceDtoList
    }

    override fun saveFertilizerPrice(operationCostRequest: OperationCostRequest): OperationCostRequest? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateOperationCost(id: Long, operationCostRequest: OperationCostRequest): OperationCost? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteOperationCost(id: Long): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}