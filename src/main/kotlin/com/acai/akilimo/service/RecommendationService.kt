package com.acai.akilimo.service

import com.acai.akilimo.config.AkilimoConfigProperties
import com.acai.akilimo.entities.Payload
import com.acai.akilimo.enums.EnumCountry
import com.acai.akilimo.enums.EnumFertilizer
import com.acai.akilimo.mapper.RecommendationResponseDto
import com.acai.akilimo.properties.PlumberProperties
import com.acai.akilimo.repositories.FertilizerRepository
import com.acai.akilimo.repositories.PayloadRepository
import com.acai.akilimo.request.FertilizerList
import com.acai.akilimo.request.PlumberComputeRequest
import com.acai.akilimo.request.RecommendationRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.joda.time.LocalDateTime
import org.joda.time.Seconds
import org.modelmapper.ModelMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

@Suppress("UNCHECKED_CAST", "CAST_NEVER_SUCCEEDS", "DuplicatedCode")
@Service
class RecommendationService
@Autowired
constructor(private val restTemplate: RestTemplate,
            val fertilizerRepository: FertilizerRepository,
            val payloadRepository: PayloadRepository,
            akilimoConfigProperties: AkilimoConfigProperties) {

    private val logger = LoggerFactory.getLogger(RecommendationService::class.java)

    private val plumberPropertiesProperties: PlumberProperties = akilimoConfigProperties.plumber()
    private val mapper = ObjectMapper()
    private val modelMapper = ModelMapper()
    private lateinit var recommendationResponseDto: RecommendationResponseDto

    fun computeRecommendations(recommendationRequest: RecommendationRequest, requestContext: String?): RecommendationResponseDto? {

        val fertilizerList = prepareFertilizerList(recommendationRequest.fertilizerList)

        val plumberComputeRequest = this.prepareFertilizerPayload(recommendationRequest, fertilizerList)
        val headers = this.setHTTPHeaders()

        val droidRequestString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(recommendationRequest)
        val plumberRequestString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(plumberComputeRequest)
        var plumberResponseString = "{}"
        logger.info("Droid payload is\n")
        logger.info(droidRequestString)
        logger.info("Plumber payload is\n")
        logger.info(plumberRequestString)

        val dateTime = LocalDateTime.now()
        try {

            val entity = HttpEntity(plumberComputeRequest, headers)
            val country = plumberComputeRequest.country
            val demoMode = plumberPropertiesProperties.demoMode
            var recommendationUrl: String? = null

            var baseUrl = plumberPropertiesProperties.baseUrl
            if (requestContext.equals("dev", ignoreCase = true)) {
                baseUrl = plumberPropertiesProperties.devUrl
                logger.info("Switched to context $requestContext")
            }
            when (country) {
                EnumCountry.NG.name -> recommendationUrl = when {
                    demoMode -> "${baseUrl}${plumberPropertiesProperties.recommendationNgDemo!!}"
                    else -> "${baseUrl}${plumberPropertiesProperties.recommendationNg!!}"
                }
                EnumCountry.TZ.name -> recommendationUrl = when {
                    demoMode -> "${baseUrl}${plumberPropertiesProperties.recommendationTzDemo!!}"
                    else -> "${baseUrl}${plumberPropertiesProperties.recommendationTz!!}"
                }
            }
            recommendationResponseDto = modelMapper.map(plumberComputeRequest, RecommendationResponseDto::class.java)

            logger.info("Going to endpoint $recommendationUrl at: $dateTime")

            val response = restTemplate.postForEntity(recommendationUrl!!, entity, Array<Any>::class.java)
            val objects = response.body
            plumberResponseString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(objects)
            if (objects != null) {
                logger.info("Plumber payload response is\n")
                logger.info(plumberRequestString)

                recommendationResponseDto.responsePayload = objects
                when {
                    !processFirstArray(objects) -> {
                        processSecondArray(objects)
                    }
                }
            }
        } catch (ex: Exception) {
            logger.error("An error occurred while processing plumber request ${ex.message}")
        }

        val now = LocalDateTime.now()
        val secondsLapsed = Seconds.secondsBetween(now, dateTime)
        logger.info("Returning response to requesting client $secondsLapsed seconds passed between $dateTime and {$now}")


        //let us save the logged requests
        val payload = Payload()
        payload.deviceToken = recommendationRequest.userInfo.deviceToken
        payload.droidRequest = droidRequestString
        payload.plumberRequest = plumberRequestString
        payload.plumberResponse = plumberResponseString

        payloadRepository.save(payload)

        return recommendationResponseDto
    }

    private fun processFirstArray(objects: Array<Any>): Boolean {
        try {
            if (objects[0] is LinkedHashMap<*, *>) {
                val computedHashMap = objects[0] as LinkedHashMap<String, ArrayList<Objects>>
                if (computedHashMap.containsKey("FR")) {
                    try {
                        val rec = computedHashMap["FR"] as LinkedHashMap<String, ArrayList<Objects>>
                    } catch (ex: Exception) {
                        logger.error("Error processing linked hash for FR, must be array, going to array next ${ex.message}")
                        val recommendation = computedHashMap["FR"]
                        if (recommendation is ArrayList<*>) {
                            val frText = computedHashMap.getValue("FR") as ArrayList<String>
                            if (frText.size == 1) {
                                recommendationResponseDto.fertilizerRecText = frText[0]
                                recommendationResponseDto.hasResponse = true
                            }
                        }
                    }
                }

                if (computedHashMap.containsKey("SP")) {
                    try {
                        val rec = computedHashMap["SP"] as LinkedHashMap<String, ArrayList<Objects>>
                    } catch (ex: Exception) {
                        logger.error("Error processing linked hash for SP, must be array, going to array next ${ex.message}")
                        val recommendation = computedHashMap["SP"]
                        if (recommendation is ArrayList<*>) {
                            val spText = computedHashMap.getValue("SP") as ArrayList<String>
                            if (spText.size == 1) {
                                recommendationResponseDto.scheduledPlantingRecText = spText[0]
                                recommendationResponseDto.hasResponse = true
                            }
                        }
                    }
                }

                if (computedHashMap.containsKey("PP")) {
                    try {
                        val rec = computedHashMap["PP"] as LinkedHashMap<String, ArrayList<Objects>>
                    } catch (ex: Exception) {
                        logger.error("Error processing linked hash for PP, must be array, going to array next ${ex.message}")
                        val recommendation = computedHashMap["PP"]
                        if (recommendation is ArrayList<*>) {
                            val ppText = computedHashMap.getValue("PP") as ArrayList<String>
                            if (ppText.size == 1) {
                                recommendationResponseDto.plantingPracticeRecText = ppText[0]
                                recommendationResponseDto.hasResponse = true
                            }
                        }
                    }
                }


                if (computedHashMap.containsKey("IC")) {
                    try {
                        val rec = computedHashMap["IC"] as LinkedHashMap<String, ArrayList<Objects>>
                    } catch (ex: Exception) {
                        logger.error("Error processing linked hash for IC, must be array, going to array next ${ex.message}")
                        //check if it is an array
                        val recommendation = computedHashMap["IC"]
                        if (recommendation is ArrayList<*>) {
                            val icText = computedHashMap.getValue("PP") as ArrayList<String>
                            if (icText.size == 1) {
                                recommendationResponseDto.interCroppingRecText = icText[0]
                                recommendationResponseDto.hasResponse = true
                            }
                        }
                    }
                }
            }

        } catch (ex: Exception) {
            recommendationResponseDto.hasResponse = false
            logger.error("An error occurred while processing first array object ${ex.message}")
        }

        return recommendationResponseDto.hasResponse
    }

    private fun processSecondArray(objects: Array<Any>): Boolean {
        try {
            if (objects[1] is LinkedHashMap<*, *>) {
                val recommendationHashMap = objects[1] as LinkedHashMap<String, ArrayList<Objects>>

                if (recommendationHashMap.containsKey("FR")) {
                    val frText = recommendationHashMap.getValue("FR") as ArrayList<String?>
                    recommendationResponseDto.fertilizerRecText = frText[0]
                    recommendationResponseDto.hasResponse = true

                }

                if (recommendationHashMap.containsKey("IC")) {
                    val icText = recommendationHashMap.getValue("IC") as ArrayList<String?>
                    recommendationResponseDto.interCroppingRecText = icText[0]
                    recommendationResponseDto.hasResponse = true
                }

                if (recommendationHashMap.containsKey("PP")) {
                    val ppText = recommendationHashMap.getValue("PP") as ArrayList<String?>
                    recommendationResponseDto.plantingPracticeRecText = ppText[0]
                    recommendationResponseDto.hasResponse = true
                }

                if (recommendationHashMap.containsKey("SP")) {
                    val spText = recommendationHashMap.getValue("SP") as ArrayList<String?>
                    recommendationResponseDto.scheduledPlantingRecText = spText[0]
                    recommendationResponseDto.hasResponse = true
                }
            }
        } catch (ex: Exception) {
            recommendationResponseDto.hasResponse = false
            logger.error("An error occurred while processing first array object ${ex.message}")
        }

        return recommendationResponseDto.hasResponse
    }


    private fun setHTTPHeaders(): HttpHeaders {
        val headers = HttpHeaders()
        headers.add("Content-Type", MediaType.APPLICATION_JSON.toString())
        return headers
    }

    private fun evaluateFertilizers(tempFertilizerList: LinkedHashMap<String, FertilizerList>): LinkedHashMap<String, FertilizerList> {
        val allFertilizers = fertilizerRepository.findAllByAvailableIsTrue()
        allFertilizers.forEach { fertilizer ->
            if (!tempFertilizerList.containsKey(fertilizer.type)) {
                val fertName = fertilizer.type!!
                val fert = modelMapper.map(fertilizer, FertilizerList::class.java)
                fert.fertilizerType = fertName
                fert.fertilizerTypeName = fertilizer.name
                fert.fertilizerWeight = 50
                fert.fertilizerCostPerBag = 0.0

                tempFertilizerList[fertName] = fert
            }
        }
        return tempFertilizerList;
    }

    private fun prepareFertilizerList(fertilizers: Set<FertilizerList>): LinkedHashMap<String, FertilizerList> {
        val fertilizerHashMap = LinkedHashMap<String, FertilizerList>()

        fertilizers.forEach { fertilizer ->
            fertilizerHashMap[fertilizer.fertilizerType!!] = fertilizer
        }

        return fertilizerHashMap
    }

    private fun prepareFertilizerPayload(recommendationRequest: RecommendationRequest, tempFertilizerList: LinkedHashMap<String, FertilizerList>): PlumberComputeRequest {
        val modelMapper = ModelMapper()

        val fertilizerList = evaluateFertilizers(tempFertilizerList)
        val requestPayloadPlumber = modelMapper.map(recommendationRequest.userInfo, PlumberComputeRequest::class.java)
        modelMapper.map(recommendationRequest.computeRequest, requestPayloadPlumber)

        if (recommendationRequest.computeRequest.interCroppingMaizeRec || recommendationRequest.computeRequest.interCroppingPotatoRec) {
            requestPayloadPlumber.intercrop = true
            requestPayloadPlumber.interCroppingRec = true
        }
        if (fertilizerList.containsKey(EnumFertilizer.UREA.name)) {
            val urea = fertilizerList[EnumFertilizer.UREA.name]!!
            requestPayloadPlumber.ureaAvailable = urea.selected
            requestPayloadPlumber.ureaBagWeight = urea.fertilizerWeight!!
            requestPayloadPlumber.ureaCostPerBag = urea.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.CAN.name)) {
            val can = fertilizerList[EnumFertilizer.CAN.name]!!
            requestPayloadPlumber.canAvailable = can.selected
            requestPayloadPlumber.canBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.canCostPerBag = can.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.SSP.name)) {
            val can = fertilizerList[EnumFertilizer.SSP.name]!!
            requestPayloadPlumber.sspAvailable = can.selected
            requestPayloadPlumber.sspBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.sspCostPerBag = can.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.MOP.name)) {
            val can = fertilizerList[EnumFertilizer.MOP.name]!!
            requestPayloadPlumber.mopAvailable = can.selected
            requestPayloadPlumber.mopBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.mopCostPerBag = can.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.DAP.name)) {
            val can = fertilizerList[EnumFertilizer.DAP.name]!!
            requestPayloadPlumber.dapAvailable = can.selected
            requestPayloadPlumber.dapBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.dapCostPerBag = can.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.TSP.name)) {
            val can = fertilizerList[EnumFertilizer.TSP.name]!!
            requestPayloadPlumber.tspAvailable = can.selected
            requestPayloadPlumber.tspBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.tspCostPerBag = can.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.NAFAKAPLUS.name)) {
            val can = fertilizerList[EnumFertilizer.NAFAKAPLUS.name]!!
            requestPayloadPlumber.nafakaAvailable = can.selected
            requestPayloadPlumber.nafakaBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.nafakaCostPerBag = can.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.YARAMILA_UNIK.name)) {
            val yaramilaUnik = fertilizerList[EnumFertilizer.YARAMILA_UNIK.name]!!
            requestPayloadPlumber.yaramilaUnikAvailable = yaramilaUnik.selected
            requestPayloadPlumber.yaramilaUnikBagWeight = yaramilaUnik.fertilizerWeight!!
            requestPayloadPlumber.yaramilaUnikCostPerBag = yaramilaUnik.fertilizerCostPerBag
        }


        if (fertilizerList.containsKey(EnumFertilizer.NPK_20_10_10.name)) {
            val can = fertilizerList[EnumFertilizer.NPK_20_10_10.name]!!
            requestPayloadPlumber.npkTwentyAvailable = can.selected
            requestPayloadPlumber.npkTwentyBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.npkTwentyCostPerBag = can.fertilizerCostPerBag
        }


        if (fertilizerList.containsKey(EnumFertilizer.NPK_17_17_17.name)) {
            val npk17 = fertilizerList[EnumFertilizer.NPK_17_17_17.name]!!
            requestPayloadPlumber.npkSeventeenAvailable = npk17.selected
            requestPayloadPlumber.npkSeventeenBagWeight = npk17.fertilizerWeight!!
            requestPayloadPlumber.npkSeventeenCostPerBag = npk17.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.NPK_15_15_15.name)) {
            val can = fertilizerList[EnumFertilizer.NPK_15_15_15.name]!!
            requestPayloadPlumber.npkFifteenAvailable = can.selected
            requestPayloadPlumber.npkFifteenBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.npkFifteenCostPerBag = can.fertilizerCostPerBag
        }

        if (fertilizerList.containsKey(EnumFertilizer.NPK_20_12_16_MG_2.name)) {
            val can = fertilizerList[EnumFertilizer.NPK_20_12_16_MG_2.name]!!
            requestPayloadPlumber.npkTwentyTwelveAvailable = can.selected
            requestPayloadPlumber.npkTwentyTwelveBagWeight = can.fertilizerWeight!!
            requestPayloadPlumber.npkTwentyTwelveCostPerBag = can.fertilizerCostPerBag
        }

        return requestPayloadPlumber
    }
}